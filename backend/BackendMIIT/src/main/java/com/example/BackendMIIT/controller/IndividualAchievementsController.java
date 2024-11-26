package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.domain.IndividualAchievements;
import com.example.BackendMIIT.service.IndividualAchievementsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @GetMapping("/")
    public List<IndividualAchievements> getAllIndividualAchievements() {
        return individualAchievementsService.getAllIndividualAchievements();
    }
}
