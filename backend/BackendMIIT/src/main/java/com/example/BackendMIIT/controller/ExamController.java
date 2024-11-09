package com.example.BackendMIIT.controller;

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

    @PostMapping("/exams/parse")
    public ResponseEntity<String> parseSubjects() {
        examService.parseAndSaveExamSubjects();
        return ResponseEntity.ok("Exam subjects parsed successfully");
    }


    @GetMapping("/exams")
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/direction")
    public ResponseEntity<List<ExamDto>> getExamsByDirection(@RequestParam String directionName) {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
