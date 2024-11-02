package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.mapper.ProfileMapper;
import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.model.dto.ProfileDto;
import com.example.BackendMIIT.repository.DirectionRepository;
import com.example.BackendMIIT.repository.ProfileRepository;
import com.example.BackendMIIT.service.ProfileService;
import com.example.BackendMIIT.util.exceptions.ImageUploadException;
import io.minio.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final DirectionRepository directionRepository;
    private final WebClient webClient;
    private final String BASE_URL = "https://www.miit.ru";
    private final ProfileMapper profileMapper;
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    @Value("${minio.url}")
    private String url;

    public ProfileServiceImpl(ProfileRepository profileRepository,
                              DirectionRepository directionRepository,
                              WebClient webClient,
                              ProfileMapper profileMapper,
                              MinioClient minioClient) {
        this.profileRepository = profileRepository;
        this.directionRepository = directionRepository;
        this.webClient = webClient;
        this.profileMapper = profileMapper;
        this.minioClient = minioClient;
    }

    @Override
    @Cacheable(value = "ProfileService::getProfilesByInstitute", key = "#name")
    public List<ProfileDto> getProfilesByInstituteName(String name) {
        List<Profile> profiles = profileRepository.findByInstitute(name)
                .orElseThrow(() -> new EntityNotFoundException("Institute doesn't exist"));

        return profileMapper.profilesToDtoList(profiles);
    }

    @Override
    @Cacheable(value = "ProfileService::getProfilesByDirection", key = "#code")
    public List<ProfileDto> getProfilesByDirectionCode(String code) {
        Direction direction = directionRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Institute doesn't exist"));

        return profileMapper.profilesToDtoList(direction.getProfiles());
    }

    @Override
    @Cacheable(value = "ProfileService::getAllProfiles", key = "'profiles'")
    public List<ProfileDto> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();

        return profileMapper.profilesToDtoList(profiles);
    }

    @Override
    @Cacheable(value = "ProfileService::getProfileByName", key = "#name")
    public ProfileDto getProfileByName(String name) {
        Profile profile = profileRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Profile doesn't exist"));
        return profileMapper.profileToDto(profile);
    }

    @Override
    public String uploadImage(MultipartFile file, String profile) {
        try {
            createBucket();
        }
        catch (Exception e) {
            throw new ImageUploadException("Image upload failed" + e.getMessage());
        }

        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new ImageUploadException("Image must have name");
        }

        String fileName = generateFileName(file);
        String imageUrl;
        try (InputStream inputStream = file.getInputStream()) {
            imageUrl = saveImage(inputStream, fileName, profile);
        }
        catch (Exception e) {
            throw new ImageUploadException(("Image upload failed" + e.getMessage()));
        }

        return imageUrl;
    }

    @SneakyThrows
    private String saveImage(InputStream inputStream, String fileName, String profileName) {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .stream(inputStream, inputStream.available(), -1)
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );

        Profile profile = profileRepository.findByName(profileName)
                .orElseThrow(() -> new EntityNotFoundException("Profile doesn't exist"));
        String imageUrl = url + "/" + fileName;

        profile.setImageUrl(imageUrl);
        profileRepository.save(profile);

        return imageUrl;
    }

    private String generateFileName(MultipartFile file) {
        String extension = getExtension(file);
        return UUID.randomUUID() + "." + extension;
    }

    private String getExtension(MultipartFile file) {
        return file.getOriginalFilename().
                substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }

    @SneakyThrows
    private void createBucket() {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
    }

    @Override
    @SneakyThrows
    public void parseProfile(String uri) {

        List<String> directionLinks = new ArrayList<>();

        String json = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject jsonObject = new JSONObject(json);

        JSONArray jsonArray = jsonObject
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONArray("concourseGroups");

        for (int i = 0; i < jsonArray.length(); i++) {
            directionLinks.add(jsonArray.getJSONObject(i).getString("planReceptionUrl"));
        }

        readDirectionLinks(directionLinks);
    }

    @SneakyThrows
    private void readDirectionLinks(List<String> directionLinks) {

        List<String> profileLinks = new ArrayList<>();
        Elements elements;

        for (String link : directionLinks) {
            Document directionPage = Jsoup.connect(BASE_URL + link).maxBodySize(0).get();

            elements = directionPage.select("a[href*=/admissions/degrees/]");
            for (Element element : elements) {
                profileLinks.add(element.attr("href"));
            }
        }

        readProfiles(profileLinks);
    }

    @SneakyThrows
    private void readProfiles(List<String> profileLinks) {

        List<String> properties = new ArrayList<>();

        for (String link : profileLinks) {
            Document profilePage = Jsoup.connect(BASE_URL + link).maxBodySize(0).get();

            String profileHeader = profilePage.select("h2").text();
            if (profileHeader.contains("набор")) continue;
            properties.add(profileHeader.substring(0, profileHeader.indexOf(" ")).trim()); //Direction code
            properties.add(profileHeader.substring(profileHeader.indexOf(". ") + 1, profileHeader.indexOf("(")).trim()); //Profile

            Elements elements = profilePage.select("li[class=text-form__item]");

            properties.add(getInstitute(elements)); //Institute
            properties.add(getGroup(profileHeader)); //Abbreviation
            saveProfile(properties);
            properties.clear();
        }
    }

    private String getGroup(String profileHeader) {

        StringBuilder sb = new StringBuilder();
        int index = profileHeader.trim().length() - 1;
        boolean isGroup = false;

        while (profileHeader.charAt(index) != '(') {
            if (isGroup) {
                sb.insert(0, profileHeader.charAt(index--));
                continue;
            }
            if (profileHeader.charAt(index--) == ')')
                isGroup = true;
        }

        return sb.toString();
    }

    private String getInstitute(Elements elements) {
        String institute = null;

        for (Element element : elements) {
            String text = element.text();
            if (text.contains("Институт")) {
                institute = text.substring(text.indexOf(" "));
            }
        }

        return institute;
    }

    @Override
    public void saveProfile(List<String> properties) {

        int i = 0;

        while (i < properties.size()) {

            Profile profile = new Profile();
            Direction direction = directionRepository.findByCode(properties.get(i++).trim())
                    .orElseThrow(() -> new EntityNotFoundException("Direction doesn't exist"));

            if (profileRepository.findByName(properties.get(i)).isEmpty()) {
                profile.setName(properties.get(i++).trim());
                profile.setLevel(direction.getLevel());
                profile.setForm(direction.getForm());
                profile.setInstitute(properties.get(i++).trim());
                profile.setAbbreviation(properties.get(i++).trim());
                profile.setDirection(direction);

                profileRepository.save(profile);
            } else {
                break;
            }
        }
    }
}
