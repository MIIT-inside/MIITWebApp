package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.configuration.UrlsConfig;
import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.DirectionExamPoints;
import com.example.BackendMIIT.model.domain.Exam;
import com.example.BackendMIIT.parser.util.ParserUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DirectionExamPointsParser {

    private final String minPointsUrl;

    public DirectionExamPointsParser(UrlsConfig urlsConfig) {
        this.minPointsUrl = urlsConfig.getMinPoints();
    }

    public Set<DirectionExamPoints> parseDirectionExamPoints(List<Exam> allExams, List<Direction> allDirections) {
        Set<DirectionExamPoints> directionExamPointsSet = new HashSet<>();

        Elements directionElements = ParserUtil.getElements(minPointsUrl, "div.tr.row");
        System.out.println("Found directions on the page: " + directionElements.size());

        for (Element directionElement : directionElements) {
            Element directionElementWithCode = directionElement.selectFirst("div.td.col-12.col-md-6.bg-light");

            if (directionElementWithCode == null) { continue; }

            String directionWithCode = directionElementWithCode.text();

            String directionName = directionWithCode.substring(directionWithCode.indexOf(' ') + 1).trim();

            Direction direction = allDirections.stream()
                    .filter(d -> d.getName().trim().equalsIgnoreCase(directionName))
                    .findFirst()
                    .orElse(null);

            if (direction == null) {
                System.out.println("Direction: " + directionWithCode + " doesn't exist in the db. Skipping");
                continue;
            }

            Elements exams = directionElement.select("div.td.col-12.col-md-6.pb-3.pb-md-0 br");

            for (Element examElement : exams) {
                String[] parts = examElement.text().split(":");

                if (parts.length < 2) { continue; }

                String subject = parts[0].trim();
                int minPoints;

                try {
                    minPoints = Integer.parseInt(parts[1].trim().replace(";", ""));
                } catch (NumberFormatException numberFormatException) {
                    continue;
                }

                Exam exam = allExams.stream()
                        .filter(e -> e.getSubjectName().trim().equalsIgnoreCase(subject))
                        .findFirst()
                        .orElse(null);

                if (exam != null && minPoints != 0) {
                    DirectionExamPoints directionExamPoints = new DirectionExamPoints();
                    directionExamPoints.setDirection(direction);
                    directionExamPoints.setExam(exam);
                    directionExamPoints.setMinPoints(minPoints);
                    directionExamPointsSet.add(directionExamPoints);
                }
            }
        }

        return directionExamPointsSet;
    }
}
