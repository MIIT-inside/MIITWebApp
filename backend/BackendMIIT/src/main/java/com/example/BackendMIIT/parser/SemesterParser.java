package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.model.domain.Semester;
import com.example.BackendMIIT.parser.util.ParserUtil;
import lombok.SneakyThrows;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SemesterParser {

    private final DisciplineParser disciplineParser;

    public SemesterParser(DisciplineParser disciplineParser) {
        this.disciplineParser = disciplineParser;
    }

    @SneakyThrows
    public List<Semester> parseSemesters(String url, Profile profile) {
        List<Semester> semesters = new ArrayList<>();

        Elements semesterElements = ParserUtil.getElements(url, "div.info-block.info-block_collapse");

        for (Element semesterElement : semesterElements) {
            String semesterName = semesterElement.select("span.info-block__header-text").text().trim();

            if (semesterName.matches(".*\\d+-й семестр.*")) {
                Semester semester = new Semester();
                semester.setName(semesterName);
                semester.setProfile(profile);
                semester.setDisciplines(disciplineParser.parseDisciplines(semesterElement));
                semesters.add(semester);
            }
        }

        return semesters;
    }
}
