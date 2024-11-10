package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.dto.DirectionExamPointsDto;
import com.example.BackendMIIT.model.dto.ExamDto;

import java.util.List;

public interface ExamService {
    void parseAndSaveExamSubjects();

    void parseAndSaveMinPoints();

    List<ExamDto> getAllExams();

    DirectionExamPointsDto getDirectionExamPoints(String directionName);
}
