package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.repositories.DirectionRepository;
import com.example.BackendMIIT.repositories.ProfileRepository;
import com.example.BackendMIIT.service.ProfileService;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final DirectionRepository directionRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository, DirectionRepository directionRepository) {
        this.profileRepository = profileRepository;
        this.directionRepository = directionRepository;
    }

    @Override
    @SneakyThrows
    public void parseProfile(String url) {

        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        String currentDirection;
        String previousDirection = "";
        String[] directionAndProfile;

        Elements elements = doc.select("tr");

        for (Element element : elements) {
            Element code = element.selectFirst("td[itemprop=eduCode]");
            Element name = element.selectFirst("td[itemprop=eduName]");
            Element level = element.selectFirst("td[itemprop=eduLevel]");
            Element form = element.selectFirst("td[itemprop=eduForm]");

            if (code != null && name != null && level != null && form != null) {
                currentDirection = code.text();
                if (!previousDirection.equals(currentDirection) && form.text().equals("очная") && (level.text().equals("бакалавриат") || level.text().equals("специалитет"))) {
                    Profile profile = new Profile();

                    directionAndProfile = name.text().split("\\. ");

                    Direction direction = directionRepository.findByName(directionAndProfile[0].trim());
                    profile.setName(directionAndProfile[1].trim());
                    profile.setForm(form.text());
                    profile.setDirection(direction);

                    profileRepository.save(profile);
                }
            }
        }
    }
}
