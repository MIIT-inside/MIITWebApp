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

import java.util.ArrayList;
import java.util.List;

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

        String previousProfile = "";
        List<Element> props = new ArrayList<>();

        Elements elements = doc.select("tr");

        for (Element element : elements) {
            props.add(element.selectFirst("td[itemprop=eduCode]"));
            props.add(element.selectFirst("td[itemprop=eduName]"));
            props.add(element.selectFirst("td[itemprop=eduLevel]"));
            props.add(element.selectFirst("td[itemprop=eduForm]"));

            previousProfile = saveProfile(props, previousProfile);

            props.clear();
        }
    }

    @Override
    public String saveProfile(List<Element> props, String previousProfile) {
        if (props.get(0) != null && props.get(1) != null && props.get(2) != null && props.get(3) != null) {

            String code = props.get(0).text();
            String name = props.get(1).text();
            String level = props.get(2).text();
            String form = props.get(3).text();

            if (!previousProfile.equals(name) && form.equals("очная") && (level.equals("бакалавриат") || level.equals("специалитет"))) {

                previousProfile = name;
                Profile profile = new Profile();

                if (name.contains(".")) {
                    name = name.substring(name.indexOf("."));
                }

                Direction direction = directionRepository.findByCode(code.trim());
                profile.setName(name.trim());
                profile.setForm(form.trim());
                profile.setDirection(direction);

                profileRepository.save(profile);
            }
        }

        return previousProfile;
    }
}
