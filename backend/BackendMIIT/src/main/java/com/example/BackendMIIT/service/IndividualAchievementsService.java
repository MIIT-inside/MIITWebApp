package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.domain.IndividualAchievements;

import java.util.List;

public interface IndividualAchievementsService {
    void parseAndSaveAchievements();

    List<IndividualAchievements> getAllIndividualAchievements();
}
