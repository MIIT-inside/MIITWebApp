package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.dto.ExamDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/miit/exams")
public class ExamController {

    @GetMapping("/")
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/direction")
    public ResponseEntity<List<ExamDto>> getExamsByDirection(@RequestParam String directionName) {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
