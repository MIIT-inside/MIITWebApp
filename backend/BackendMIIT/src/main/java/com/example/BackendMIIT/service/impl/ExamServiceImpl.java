package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Exam;
import com.example.BackendMIIT.parser.ExamParser;
import com.example.BackendMIIT.repository.ExamRepository;
import com.example.BackendMIIT.service.ExamService;

import java.util.List;
import java.util.Set;

public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ExamParser parser;

    public ExamServiceImpl(ExamRepository examRepository, ExamParser parser) {
        this.examRepository = examRepository;
        this.parser = parser;
    }

    @Override
    public void parseAndSaveExamSubjects() {
        Set<Exam> exams = parser.parseExams();
        examRepository.saveAll(exams);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }
}
