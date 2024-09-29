package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.domain.Educator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/miit/educators")
public class EducatorController {

    @GetMapping
    public ResponseEntity<List<Educator>> getAllEducators() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping
    public ResponseEntity<List<Educator>> getEducatorsByDepartment(@RequestParam String departmentName) {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
