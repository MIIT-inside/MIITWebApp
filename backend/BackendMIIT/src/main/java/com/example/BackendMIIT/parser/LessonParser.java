package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.model.domain.Lesson;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonParser {

    public List<Lesson> parseLessons(Element disciplineElement) {
        List<Lesson> lessons = new ArrayList<>();
        String lessonCSS = "div.eduplan_discipline-content-header:contains(Виды занятий) + ul li";
        Elements lessonElements = disciplineElement.parent().select(lessonCSS);

        Lesson lesson = new Lesson();
        for (Element lessonElement : lessonElements) {
            String lessonText = lessonElement.text().trim();
            if (lessonText.startsWith("Лекция")) {
                lesson.setLecture(lessonText.replace("Лекция:", "").trim());
            } else if (lessonText.startsWith("Практическое занятие")) {
                lesson.setPractice(lessonText.replace("Практическое занятие:", "").trim());
            } else if (lessonText.startsWith("Лабораторная работа")) {
                lesson.setLaboratoryWork(lessonText.replace("Лабораторная работа:", "").trim());
            }
        }
        lessons.add(lesson);

        return lessons;
    }
}

