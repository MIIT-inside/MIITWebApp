package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.repository.DirectionRepository;
import com.example.BackendMIIT.repository.ProfileRepository;
import com.example.BackendMIIT.service.ProfileService;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final DirectionRepository directionRepository;
    private final WebClient webClient;
    private final String BASE_URL = "https://www.miit.ru";

    public ProfileServiceImpl(ProfileRepository profileRepository, DirectionRepository directionRepository, WebClient webClient) {
        this.profileRepository = profileRepository;
        this.directionRepository = directionRepository;
        this.webClient = webClient;
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
            Direction direction = directionRepository.findByCode(properties.get(i++).trim());

            if (profileRepository.findByName(properties.get(i)).isEmpty()) {
                profile.setName(properties.get(i++).trim());
                profile.setLevel(direction.getLevel());
                profile.setForm(direction.getForm());
                profile.setInstitute(properties.get(i++).trim());
                profile.setAbbreviation(properties.get(i++).trim());
                profile.setDirection(direction);

                profileRepository.save(profile);
            }
            else {
                break;
            }
        }
    }
}
