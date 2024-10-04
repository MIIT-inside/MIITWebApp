package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.Category;
import com.example.BackendMIIT.model.domain.PassPoint;
import com.example.BackendMIIT.repositories.DirectionRepository;
import com.example.BackendMIIT.repositories.PassPointRepository;
import com.example.BackendMIIT.service.PassPointService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


@Service
public class PassPointServiceImpl implements PassPointService {

    @Autowired
    private PassPointRepository passPointRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private WebClient webClient;

    @Override
    public void savePoints(String uri) {

        List<JSONObject> doublesValues = new ArrayList<>();

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

            JSONObject groups = jsonArray.getJSONObject(i);
            JSONObject doubles = groups.getJSONObject("planNumbers").getJSONObject("doubles");

            doublesValues.add(doubles.getJSONObject("CONCOURSE"));
            doublesValues.add(doubles.getJSONObject("SPECIAL_QUOTA"));
            doublesValues.add(doubles.getJSONObject("TARGET_QUOTA"));
            doublesValues.add(doubles.getJSONObject("QUOTA_07"));
            doublesValues.add(doubles.getJSONObject("PAID_CONCOURSE"));

            Direction direction = directionRepository.findByName(groups.getString("specName").trim());

            for (int j = 0; j < doublesValues.size(); j++) {
                PassPoint passPoint = new PassPoint();

                int avg = (int) doublesValues.get(j).optDouble("AVERAGE_SCORE");
                int min = (int) doublesValues.get(j).optDouble("MIN_SCORE");

                passPoint.setDirection(direction);

                switch (j) {
                    case 0 -> passPoint.setCategory(Category.MAIN);
                    case 1 -> passPoint.setCategory(Category.SPECIAL);
                    case 2 -> passPoint.setCategory(Category.TARGET);
                    case 3 -> passPoint.setCategory(Category.SEPARATE);
                    case 4 -> passPoint.setCategory(Category.CONTRACT);
                }

                passPoint.setAvg(avg);
                passPoint.setMin(min);

                passPointRepository.save(passPoint);
            }
            doublesValues.clear();
        }
    }
}
