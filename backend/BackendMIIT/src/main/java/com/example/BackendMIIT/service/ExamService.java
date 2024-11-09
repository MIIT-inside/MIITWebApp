package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.domain.Exam;

import java.util.Set;

public interface ExamService {
    void parseAndSaveExamSubjects();

    Set<Exam> getAllExams();
}
