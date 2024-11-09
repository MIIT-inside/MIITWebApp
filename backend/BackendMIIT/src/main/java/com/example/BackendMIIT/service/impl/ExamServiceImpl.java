package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.mapper.ExamMapper;
import com.example.BackendMIIT.model.domain.Exam;
import com.example.BackendMIIT.model.dto.ExamDto;
import com.example.BackendMIIT.parser.ExamParser;
import com.example.BackendMIIT.repository.ExamRepository;
import com.example.BackendMIIT.service.ExamService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final ExamParser parser;

    public ExamServiceImpl(ExamRepository examRepository, ExamMapper examMapper, ExamParser parser) {
        this.examRepository = examRepository;
        this.examMapper = examMapper;
        this.parser = parser;
    }

    @Override
    public void parseAndSaveExamSubjects() {
        Set<Exam> exams = parser.parseExams();
        examRepository.saveAll(exams);
    }

    @Override
    public List<ExamDto> getAllExams() {
        return examRepository.findAll()
                             .stream()
                             .map(examMapper::examToDto)
                             .collect(Collectors.toList());
    }
}
