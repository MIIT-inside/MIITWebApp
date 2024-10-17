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

            Element disciplineContent = disciplineElement.nextElementSibling();

            if (disciplineContent != null) {
                String attestationCSS = "div.eduplan_discipline-content-header:contains(Форма промежуточной аттестации) + ul li";
                Element attestationElement = disciplineContent.selectFirst(attestationCSS);

                if (attestationElement != null) {
                    String attestationText = attestationElement.text().replace("\"", "").trim();
                    discipline.setAttestation(attestationText);
                } else {
                    discipline.setAttestation("Не указана");
                }
            } else {
                discipline.setAttestation("Не указана");
            }
            disciplines.add(discipline);
        }

        return disciplines;
    }
}
