package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.repositories.DirectionRepository;
import com.example.BackendMIIT.repositories.ProfileRepository;
import com.example.BackendMIIT.service.DirectionService;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    public DirectionServiceImpl(DirectionRepository directionRepository, ProfileRepository profileRepository) {
        this.directionRepository = directionRepository;
    }

    @Override
    @SneakyThrows
    public void parseDirections(String url) {

        Document doc = Jsoup.connect(url).maxBodySize(0).get();

        List<Element> list = new ArrayList<>();
        String previousDirection = "";

        Elements elements = doc.select("tr");

        for (Element element : elements) {
            list.add(element.selectFirst("td[itemprop=eduCode]"));
            list.add(element.selectFirst("td[itemprop=eduName]"));
            list.add(element.selectFirst("td[itemprop=eduLevel]"));
            list.add(element.selectFirst("td[itemprop=eduForm]"));

            previousDirection = saveDirection(list, previousDirection);
            list.clear();
        }
    }

    @Override
    public String saveDirection(List<Element> elements, String previousDirection) {
        if (elements.get(0) != null && elements.get(1) != null && elements.get(2) != null && elements.get(3) != null) {

            String code = elements.get(0).text();
            String name = elements.get(1).text();
            String level = elements.get(2).text();
            String form = elements.get(3).text();

            if (!previousDirection.equals(code) && form.equals("очная") && (level.equals("бакалавриат") || level.equals("специалитет"))) {
                Direction direction = new Direction();

                String nameDirection = name.substring(0, name.indexOf("."));

                direction.setCode(code.trim());
                direction.setName(nameDirection.trim());
                direction.setLevel(level.trim());
                direction.setForm(form.trim());

                directionRepository.save(direction);
            }
            return code;
        }

        return previousDirection;
    }
}
