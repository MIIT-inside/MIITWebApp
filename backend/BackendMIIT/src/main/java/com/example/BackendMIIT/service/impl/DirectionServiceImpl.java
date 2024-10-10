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

        Elements elements = doc.select("tr");

        for (Element element : elements) {
            props.add(element.selectFirst("td[itemprop=eduCode]"));
            props.add(element.selectFirst("td[itemprop=eduName]"));
            props.add(element.selectFirst("td[itemprop=eduLevel]"));
            props.add(element.selectFirst("td[itemprop=eduForm]"));

            saveDirection(props);
            props.clear();
        }
    }

    @Override
    public void saveDirection(List<Element> props) {

        if (props.get(0) != null && props.get(1) != null && props.get(2) != null && props.get(3) != null) {

            String code = props.get(0).text().trim();
            String name = props.get(1).text().trim();
            String level = props.get(2).text().trim();
            String form = props.get(3).text().trim();

            if (directionRepository.findByCode(code) == null && form.equals("очная") && (level.equals("бакалавриат") || level.equals("специалитет"))) {

                Direction direction = new Direction();

                if (name.contains(".")) {
                    name = name.substring(0, name.indexOf("."));
                }

                direction.setCode(code);
                direction.setName(name);
                direction.setLevel(level);
                direction.setForm(form);

                directionRepository.save(direction);
            }
        }
    }
}
