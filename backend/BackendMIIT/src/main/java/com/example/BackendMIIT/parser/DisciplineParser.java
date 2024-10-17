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

            Element disciplineContent = disciplineElement.nextElementSibling();

            if (disciplineContent != null) {
                String attestationCSS = "div.eduplan_discipline-content-header:contains(Форма промежуточной аттестации) + ul li";
                Elements attestationElements = disciplineContent.select(attestationCSS);

                boolean hasAttestation = false;

                if (!attestationElements.isEmpty()) {
                    for (Element attestationElement : attestationElements) {
                        Discipline discipline = new Discipline();
                        discipline.setName(disciplineName);
                        discipline.setLessons(lessonParser.parseLessons(disciplineElement));

                        String attestationText = attestationElement.text().replace("\"", "").trim();
                        discipline.setAttestation(attestationText);

                        disciplines.add(discipline);
                        hasAttestation = true;
                    }
                }

                String finalAttestationCSS = "div.eduplan_discipline-content-header:contains(Форма итоговой аттестации) + ul li";
                Element finalAttestationElement = disciplineContent.selectFirst(finalAttestationCSS);

                if (finalAttestationElement != null) {
                    Discipline discipline = new Discipline();
                    discipline.setName(disciplineName);
                    discipline.setLessons(new ArrayList<>());
                    String finalAttestationText = finalAttestationElement.text().replace("\"", "").trim();
                    discipline.setAttestation(finalAttestationText);
                    disciplines.add(discipline);
                } else if (!hasAttestation) {
                    Discipline discipline = new Discipline();
                    discipline.setName(disciplineName);
                    discipline.setLessons(lessonParser.parseLessons(disciplineElement));
                    discipline.setAttestation("Не указана");
                    disciplines.add(discipline);
                }

            } else {
                Discipline discipline = new Discipline();
                discipline.setName(disciplineName);
                discipline.setLessons(lessonParser.parseLessons(disciplineElement));
                discipline.setAttestation("Не указана");

                disciplines.add(discipline);
            }
        }

        return disciplines;
    }
}

