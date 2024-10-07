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
        List<Element> props = new ArrayList<>();

        Elements elements = doc.select("tr");

        for (Element element : elements) {
            props.add(element.selectFirst("td[itemprop=eduCode]"));
            props.add(element.selectFirst("td[itemprop=eduName]"));
            props.add(element.selectFirst("td[itemprop=eduLevel]"));
            props.add(element.selectFirst("td[itemprop=eduForm]"));

            saveProfile(props);
            props.clear();
        }
    }

    @Override
    public void saveProfile(List<Element> props) {

        if (props.get(0) != null && props.get(1) != null && props.get(2) != null && props.get(3) != null) {

            String code = props.get(0).text().trim();
            String name = props.get(1).text().trim(); //TODO { Parse profile names by https://www.miit.ru/admissions/degrees?year=2024&city=1&level=4&training=20773 }
            String level = props.get(2).text().trim();
            String form = props.get(3).text().trim();

            if (name.contains(".")) {
                name = name.substring(name.indexOf("."+1));
            }

            if (profileRepository.findByName(name) == null && form.equals("очная") && (level.equals("бакалавриат") || level.equals("специалитет"))) {

                Profile profile = new Profile();

                Direction direction = directionRepository.findByCode(code);
                profile.setName(name);
                profile.setForm(form);
                profile.setDirection(direction);

                profileRepository.save(profile);
            }
        }
    }
}
