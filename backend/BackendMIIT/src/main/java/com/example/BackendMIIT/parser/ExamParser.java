package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.configuration.UrlsConfig;
import com.example.BackendMIIT.model.domain.Exam;
import com.example.BackendMIIT.parser.util.ParserUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ExamParser {

    private final String minPointsUrl;

    public ExamParser(UrlsConfig urlsConfig) {
        this.minPointsUrl = urlsConfig.getMinPoints();
    }

    public Set<Exam> parseExams() {
        Set<Exam> exams = new HashSet<>();

        Elements subjectElements = ParserUtil.getElements(minPointsUrl, "div.td.col-12.col-md-6.pb-3.pb-md-0");

        for (Element element : subjectElements) {
            String[] records = element.text().split(";");

            for (String record : records) {
                String subjectName = record.split(":")[0].trim();

                if (!ParserUtil.isNullOrEmpty(subjectName)) {
                    Exam exam = new Exam();
                    exam.setSubjectName(subjectName);
                    exams.add(exam);
                }
            }
        }

        for (Exam exam : exams) {
            System.out.println(exam.getSubjectName());
        }

        return exams;
    }
}
