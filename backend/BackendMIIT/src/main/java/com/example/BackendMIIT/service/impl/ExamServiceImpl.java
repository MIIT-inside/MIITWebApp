package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.mapper.ExamMapper;
import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.DirectionExamPoints;
import com.example.BackendMIIT.model.domain.Exam;
import com.example.BackendMIIT.model.dto.ExamDto;
import com.example.BackendMIIT.parser.DirectionExamPointsParser;
import com.example.BackendMIIT.parser.ExamParser;
import com.example.BackendMIIT.repository.DirectionExamPointsRepository;
import com.example.BackendMIIT.repository.DirectionRepository;
import com.example.BackendMIIT.repository.ExamRepository;
import com.example.BackendMIIT.service.ExamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final DirectionRepository directionRepository;
    private final DirectionExamPointsRepository depRepository;
    private final ExamMapper examMapper;
    private final ExamParser examParser;
    private final DirectionExamPointsParser depParser;

    public ExamServiceImpl(ExamRepository examRepository, DirectionRepository directionRepository,
                           DirectionExamPointsRepository depRepository,
                           ExamMapper examMapper,
                           ExamParser examParser,
                           DirectionExamPointsParser depParser) {
        this.examRepository = examRepository;
        this.directionRepository = directionRepository;
        this.depRepository = depRepository;
        this.examMapper = examMapper;
        this.examParser = examParser;
        this.depParser = depParser;
    }

    @Override
    public void parseAndSaveExamSubjects() {
        Set<Exam> exams = examParser.parseExams();

        if (examRepository.findAll().isEmpty()) {
            examRepository.saveAll(exams);
        }
    }

    @Override
    public void parseAndSaveMinPoints() {
        List<Exam> allExams = examRepository.findAll();
        List<Direction> allDirections = directionRepository.findAll();

        Set<DirectionExamPoints> directionExamPoints = depParser
                .parseDirectionExamPoints(allExams, allDirections);

        if (depRepository.findAll().isEmpty()) {
            depRepository.saveAll(directionExamPoints);
        }
    }

    @Override
    public List<ExamDto> getAllExams() {
        return examRepository.findAll()
                             .stream()
                             .map(examMapper::examToDto)
                             .collect(Collectors.toList());
    }
}
