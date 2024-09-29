package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.dto.ExamDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/miit/exams")
public class ExamController {

    @GetMapping
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @GetMapping
    public ResponseEntity<ExamDto> getExamByDirection(@RequestParam String directionName) {
        return ResponseEntity.ok().body(new ExamDto());
    }
}