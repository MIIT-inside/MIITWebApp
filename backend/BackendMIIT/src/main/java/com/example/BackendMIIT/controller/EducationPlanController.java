package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.service.EducationPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/miit/plans")
public class EducationPlanController {
    private final EducationPlanService educationPlanService;

    public EducationPlanController(EducationPlanService educationPlanService) {
        this.educationPlanService = educationPlanService;
    }

    @PostMapping("/parse")
    public ResponseEntity<String> parseAndSaveEducationPlans() {
        educationPlanService.parseAndSaveEducationPlans();
        return ResponseEntity.ok("Education plans parsed and saved successfully");
    }
}
