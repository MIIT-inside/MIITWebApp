package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.repositories.DirectionRepository;
import com.example.BackendMIIT.repositories.ProfileRepository;
import com.example.BackendMIIT.service.DirectionService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    public DirectionServiceImpl(DirectionRepository directionRepository, ProfileRepository profileRepository) {
        this.directionRepository = directionRepository;
    }

    @Override
    @SneakyThrows
    public void addDirection(String url) {

        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        String currentDirection;
        String previousDirection = "";
        String nameText;

        Elements elements = doc.select("tr");

        for (Element element : elements) {
            Element code = element.selectFirst("td[itemprop=eduCode]");
            Element name = element.selectFirst("td[itemprop=eduName]");
            Element level = element.selectFirst("td[itemprop=eduLevel]");
            Element form = element.selectFirst("td[itemprop=eduForm]");

            if (code != null && name != null && level != null && form != null) {
                currentDirection = code.text();
                if (!previousDirection.equals(currentDirection) && form.text().equals("очная") && (level.text().equals("бакалавриат") || level.text().equals("специалитет"))) {
                    Direction direction = new Direction();

                    nameText = name.text().substring(0, name.text().indexOf("\\."));

                    direction.setCode(code.text().trim());
                    direction.setName(nameText.trim());
                    direction.setLevel(level.text().trim());

                    directionRepository.save(direction);
                }
            }
        }
    }
}
