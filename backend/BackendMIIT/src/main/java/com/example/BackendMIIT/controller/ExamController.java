package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.dto.DirectionExamPointsDto;
import com.example.BackendMIIT.model.dto.ExamDto;
import com.example.BackendMIIT.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/miit/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/parse")
    public ResponseEntity<String> parseSubjects() {
        examService.parseAndSaveExamSubjects();
        return ResponseEntity.ok("Exam subjects parsed successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @PostMapping("/points/parse")
    public ResponseEntity<String> parseExamsAndPoints() {
        examService.parseAndSaveMinPoints();
        return ResponseEntity.ok("Exams and min points parsed successfully");
    }

    @GetMapping("/direction")
    public ResponseEntity<DirectionExamPointsDto> getExamsByDirection(@RequestParam String directionName) {
        return ResponseEntity.ok(examService.getDirectionExamPoints(directionName));
    }

    @GetMapping("/all-directions")
    public ResponseEntity<List<DirectionExamPointsDto>> getExams() {
        return ResponseEntity.ok(examService.getAllDirectionExamPoints());
    }
}
