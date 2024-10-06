package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.domain.IndividualAchievements;
import com.example.BackendMIIT.service.IndividualAchievementsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "api/miit/individual-achievements")
public class IndividualAchievementsController {
    private final IndividualAchievementsService individualAchievementsService;

    public IndividualAchievementsController(IndividualAchievementsService individualAchievementsService) {
        this.individualAchievementsService = individualAchievementsService;
    }

    @PostMapping("/parse-and-save")
    public ResponseEntity<String> parseAndSaveIndividualAchievements() {
        Set<IndividualAchievements> achievements = individualAchievementsService.parseAchievements();
        individualAchievementsService.saveAchievements(achievements);

        return ResponseEntity.ok("Individual achievements parsed and saved successfully!");
    }
}
