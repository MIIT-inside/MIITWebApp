package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.repositories.DirectionRepository;
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

    public DirectionServiceImpl(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    @Override
    @SneakyThrows
    public void parseDirections(String url) {

        Document doc = Jsoup.connect(url).maxBodySize(0).get();

        List<Element> props = new ArrayList<>();
        String previousDirection = "";

        Elements elements = doc.select("tr");

        for (Element element : elements) {
            props.add(element.selectFirst("td[itemprop=eduCode]"));
            props.add(element.selectFirst("td[itemprop=eduName]"));
            props.add(element.selectFirst("td[itemprop=eduLevel]"));
            props.add(element.selectFirst("td[itemprop=eduForm]"));

            previousDirection = saveDirection(props, previousDirection);
            props.clear();
        }
    }

    @Override
    public String saveDirection(List<Element> props, String previousDirection) {
        if (props.get(0) != null && props.get(1) != null && props.get(2) != null && props.get(3) != null) {

            String code = props.get(0).text();
            String name = props.get(1).text();
            String level = props.get(2).text();
            String form = props.get(3).text();

            if (!previousDirection.equals(code) && form.equals("очная") && (level.equals("бакалавриат") || level.equals("специалитет"))) {

                previousDirection = code;
                Direction direction = new Direction();

                String nameDirection = name.substring(0, name.indexOf("."));

                direction.setCode(code.trim());
                direction.setName(nameDirection.trim());
                direction.setLevel(level.trim());
                direction.setForm(form.trim());

                directionRepository.save(direction);
            }
        }

        return previousDirection;
    }
}
