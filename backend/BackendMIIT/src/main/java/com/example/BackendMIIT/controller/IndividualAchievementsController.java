package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.domain.IndividualAchievements;
import com.example.BackendMIIT.service.IndividualAchievementsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/miit/achievements")
public class IndividualAchievementsController {
    private final IndividualAchievementsService individualAchievementsService;

    public IndividualAchievementsController(IndividualAchievementsService individualAchievementsService) {
        this.individualAchievementsService = individualAchievementsService;
    }

    @PostMapping("/parse")
    public ResponseEntity<String> parseAndSaveIndividualAchievements() {
        individualAchievementsService.parseAndSaveAchievements();
        return ResponseEntity.ok("Individual achievements parsed and saved successfully!");
    }

    @GetMapping
    public List<IndividualAchievements> getAllIndividualAchievements() {
        return individualAchievementsService.getAllIndividualAchievements();
    }
}
