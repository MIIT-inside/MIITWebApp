package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Discipline;
import com.example.BackendMIIT.model.domain.Lesson;
import com.example.BackendMIIT.model.domain.Semester;
import com.example.BackendMIIT.parser.AnnotationProgramsParser;
import com.example.BackendMIIT.parser.SemesterParser;
import com.example.BackendMIIT.repositories.DisciplineRepository;
import com.example.BackendMIIT.repositories.LessonRepository;
import com.example.BackendMIIT.repositories.SemesterRepository;
import com.example.BackendMIIT.service.EducationPlanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationPlanServiceImpl implements EducationPlanService {

    private final AnnotationProgramsParser annotationProgramsParser;
    private final SemesterParser semesterParser;
    private final SemesterRepository semesterRepository;
    private final DisciplineRepository disciplineRepository;
    private final LessonRepository lessonRepository;

    public EducationPlanServiceImpl(AnnotationProgramsParser annotationProgramsParser,
                                    SemesterParser semesterParser,
                                    SemesterRepository semesterRepository,
                                    DisciplineRepository disciplineRepository,
                                    LessonRepository lessonRepository) {
        this.annotationProgramsParser = annotationProgramsParser;
        this.semesterParser = semesterParser;
        this.semesterRepository = semesterRepository;
        this.disciplineRepository = disciplineRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    @Transactional
    public void parseAndSaveEducationPlans() {
        List<String> annotationUrls = annotationProgramsParser.parseAnnotations();

        for (String url : annotationUrls) {
            List<Semester> semesters = semesterParser.parseSemesters(url);

            for (Semester semester : semesters) {
                Semester savedSemester = semesterRepository.save(semester);

                for (Discipline discipline : savedSemester.getDisciplines()) {
                    discipline.setSemester(savedSemester);
                    Discipline savedDiscipline = disciplineRepository.save(discipline);

                    for (Lesson lesson : savedDiscipline.getLessons()) {
                        lesson.setDiscipline(savedDiscipline);
                        lessonRepository.save(lesson);
                    }
                }
            }
        }
    }
}

