package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.dto.EducationPlanDto;
import com.example.BackendMIIT.service.EducationPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/miit/plans")
public class EducationPlanController {

    private final EducationPlanService educationPlanService;

    public EducationPlanController(EducationPlanService educationPlanService) {
        this.educationPlanService = educationPlanService;
    }

    @GetMapping("/")
    public ResponseEntity<EducationPlanDto> getPlansByProfile(@RequestParam String name) {
        return ResponseEntity.ok(educationPlanService.getPlansByProfile(name));
    }

    @PostMapping("/parse")
    public ResponseEntity<String> parseAndSaveEducationPlans() {
        educationPlanService.parseAndSaveEducationPlans();
        return ResponseEntity.ok("Education plans parsed and saved successfully");
    }
}
