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

        Elements directionElements = ParserUtil.getElements(minPointsUrl, "div.tr_row");

        for (Element directionElement : directionElements) {
            String directionWithCode = ParserUtil
                    .getStringFromElement(directionElement, "div.td.col-12.col-md-6.bg-light");
            String directionName = directionWithCode.substring(directionWithCode.indexOf(' ') + 1).trim();

            Direction direction = allDirections.stream()
                    .filter(d -> d.getName().equalsIgnoreCase(directionName))
                    .findFirst()
                    .orElse(null);

            if (direction == null) { continue; }

            Elements exams = directionElements.select("div.td.col-12.col-md-6.pb-3.pb-md-0 br");

            for (Element examElement : exams) {
                String[] parts = examElement.text().split(":");

                if (parts.length < 2) { continue; }

                String subject = parts[0].trim();
                int minPoints;

                try {
                    minPoints = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException numberFormatException) {
                    continue;
                }

                Exam exam = allExams.stream()
                        .filter(e -> e.getSubjectName().equalsIgnoreCase(subject))
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

        for (DirectionExamPoints dep : directionExamPointsSet) {
            System.out.println(dep.getDirection() + " " + dep.getExam() + " " + dep.getMinPoints());
        }

        return directionExamPointsSet;
    }
}
