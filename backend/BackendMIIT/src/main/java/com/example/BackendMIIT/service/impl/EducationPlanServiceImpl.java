package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.*;
import com.example.BackendMIIT.parser.AnnotationProgramsParser;
import com.example.BackendMIIT.parser.SemesterParser;
import com.example.BackendMIIT.parser.util.ParserUtil;
import com.example.BackendMIIT.repositories.DisciplineRepository;
import com.example.BackendMIIT.repositories.LessonRepository;
import com.example.BackendMIIT.repositories.ProfileRepository;
import com.example.BackendMIIT.repositories.SemesterRepository;
import com.example.BackendMIIT.service.EducationPlanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EducationPlanServiceImpl implements EducationPlanService {

    private final AnnotationProgramsParser annotationProgramsParser;
    private final SemesterParser semesterParser;
    private final SemesterRepository semesterRepository;
    private final DisciplineRepository disciplineRepository;
    private final LessonRepository lessonRepository;
    private final ProfileRepository profileRepository;

    public EducationPlanServiceImpl(AnnotationProgramsParser annotationProgramsParser,
                                    SemesterParser semesterParser,
                                    SemesterRepository semesterRepository,
                                    DisciplineRepository disciplineRepository,
                                    LessonRepository lessonRepository,
                                    ProfileRepository profileRepository) {
        this.annotationProgramsParser = annotationProgramsParser;
        this.semesterParser = semesterParser;
        this.semesterRepository = semesterRepository;
        this.disciplineRepository = disciplineRepository;
        this.lessonRepository = lessonRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public void parseAndSaveEducationPlans() {
        List<String> annotationUrls = annotationProgramsParser.parseAnnotations();

        for (String url : annotationUrls) {
            String profileName = parseProfileNameFromUrl(url);

            Optional<Profile> optionalProfile = profileRepository.findByName(profileName);

            if (optionalProfile.isPresent()) {
                Profile profile = optionalProfile.get();

                List<Semester> semesters = semesterParser.parseSemesters(url, profile);

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
            } else {
                System.out.println("Skip profile: " + profileName);
            }
        }
    }

    private String parseProfileNameFromUrl(String url) {
        try {
            String fullText = Objects
                    .requireNonNull(ParserUtil
                            .getElements(url, "div.info-block .info-block__header-text")
                            .first())
                    .text()
                    .trim();

            return fullText.replaceAll(".*\\.\\s*", "").trim();
        } catch (IOException ioException) {
            return null;
        }
    }
}
