package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.repositories.DirectionRepository;
import com.example.BackendMIIT.repositories.ProfileRepository;
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

    public ProfileServiceImpl(ProfileRepository profileRepository, DirectionRepository directionRepository, WebClient webClient) {
        this.profileRepository = profileRepository;
        this.directionRepository = directionRepository;
        this.webClient = webClient;
    }

    @Override
    @SneakyThrows
    public void parseProfile(String uri) {

        List<JSONObject> profiles = new ArrayList<>();

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

            profiles.add(jsonArray.getJSONArray(22)
                    .getJSONObject(i));

            String directionCode = jsonArray.getString(6).trim();

            saveProfile(profiles, directionCode);
            profiles.clear();
        }
    }

    @Override
    public void saveProfile(List<JSONObject> profiles, String directionCode) {

        if (profiles.get(0) != null && profiles.get(1) != null && profiles.get(2) != null && profiles.get(3) != null) {

            String code = profiles.get(0).text().trim();
            String name = profiles.get(1).text().trim(); //TODO { Parse profile names by https://www.miit.ru/admissions/degrees?year=2024&city=1&level=4&training=20773 }
            String level = profiles.get(2).text().trim();
            String form = profiles.get(3).text().trim();

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
