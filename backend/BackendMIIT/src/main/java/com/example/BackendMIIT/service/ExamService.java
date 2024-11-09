package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.domain.Exam;

import java.util.List;

public interface ExamService {
    void parseAndSaveExamSubjects();

    List<Exam> getAllExams();
}
