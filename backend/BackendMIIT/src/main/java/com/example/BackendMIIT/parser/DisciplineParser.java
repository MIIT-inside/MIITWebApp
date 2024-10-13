package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.model.domain.Discipline;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DisciplineParser {

    private final LessonParser lessonParser;

    public DisciplineParser(LessonParser lessonParser) {
        this.lessonParser = lessonParser;
    }

    public List<Discipline> parseDisciplines(Element semesterElement) {
        List<Discipline> disciplines = new ArrayList<>();
        Elements disciplineElements = semesterElement.select("h4.eduplan_discipline-header");

        for (Element disciplineElement : disciplineElements) {
            String disciplineName = disciplineElement.text();
            Discipline discipline = new Discipline();
            discipline.setName(disciplineName);
            discipline.setLessons(lessonParser.parseLessons(disciplineElement));

            String attestationCSS = "div.eduplan_discipline-content-header:contains(Форма промежуточной аттестации) + ul li";
            Element attestationElement = disciplineElement.parent().selectFirst(attestationCSS);

            if (attestationElement != null) {
                discipline.setAttestation(attestationElement.text().trim());
            }

            disciplines.add(discipline);
        }

        return disciplines;
    }
}

