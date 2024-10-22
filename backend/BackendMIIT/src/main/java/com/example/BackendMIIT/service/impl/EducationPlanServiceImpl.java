package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.mapper.SemesterMapper;
import com.example.BackendMIIT.model.domain.Discipline;
import com.example.BackendMIIT.model.domain.Lesson;
import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.model.domain.Semester;
import com.example.BackendMIIT.model.dto.EducationPlanDto;
import com.example.BackendMIIT.model.dto.SemesterDto;
import com.example.BackendMIIT.parser.AnnotationProgramsParser;
import com.example.BackendMIIT.parser.SemesterParser;
import com.example.BackendMIIT.parser.util.ParserUtil;
import com.example.BackendMIIT.repositories.DisciplineRepository;
import com.example.BackendMIIT.repositories.LessonRepository;
import com.example.BackendMIIT.repositories.ProfileRepository;
import com.example.BackendMIIT.repositories.SemesterRepository;
import com.example.BackendMIIT.service.EducationPlanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    private final SemesterMapper semesterMapper;

    public EducationPlanServiceImpl(AnnotationProgramsParser annotationProgramsParser,
                                    SemesterParser semesterParser,
                                    SemesterRepository semesterRepository,
                                    DisciplineRepository disciplineRepository,
                                    LessonRepository lessonRepository,
                                    ProfileRepository profileRepository,
                                    SemesterMapper semesterMapper) {
        this.annotationProgramsParser = annotationProgramsParser;
        this.semesterParser = semesterParser;
        this.semesterRepository = semesterRepository;
        this.disciplineRepository = disciplineRepository;
        this.lessonRepository = lessonRepository;
        this.profileRepository = profileRepository;
        this.semesterMapper = semesterMapper;
    }

    @Override
    public EducationPlanDto getPlansByProfile(String name) {
        Profile profile = profileRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Profile doesn't exist"));
        List<Semester> semesters = profile.getSemesters();

        EducationPlanDto planDto = new EducationPlanDto();
        planDto.setProfileName(name);
        planDto.setSemesters(semesterConverter(semesters));
        return planDto;
    }


    private List<SemesterDto> semesterConverter(List<Semester> semesters) {
        return semesterMapper.semestersToDto(semesters);
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
        String fullText = Objects
                .requireNonNull(ParserUtil
                        .getElements(url, "div.info-block .info-block__header-text")
                        .first())
                .text()
                .trim();

        return fullText.replaceAll(".*\\.\\s*", "").trim();
    }
}
