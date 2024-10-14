package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.model.domain.Semester;
import com.example.BackendMIIT.parser.util.ParserUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SemesterParser {

    private final DisciplineParser disciplineParser;

    public SemesterParser(DisciplineParser disciplineParser) {
        this.disciplineParser = disciplineParser;
    }

    public List<Semester> parseSemesters(String url) {
        List<Semester> semesters = new ArrayList<>();

        try {
            Elements semesterElements = ParserUtil.getElements(url, "div.info-block");

            for (Element semesterElement : semesterElements) {
                String semesterName = semesterElement.select("span.info-block_header-text").text();
                Semester semester = new Semester();
                semester.setName(semesterName);
                semester.setDisciplines(disciplineParser.parseDisciplines(semesterElement));
                semesters.add(semester);
            }
        } catch (IOException e) {
            System.err.println("Semester parse exception: " + e.getMessage());
        }

        return semesters;
    }
}

