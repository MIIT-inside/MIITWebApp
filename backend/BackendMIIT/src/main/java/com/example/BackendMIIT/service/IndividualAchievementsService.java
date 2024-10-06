package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.domain.IndividualAchievements;

import java.util.Set;

public interface IndividualAchievementsService {
    Set<IndividualAchievements> parseAchievements();

    void saveAchievements(Set<IndividualAchievements> achievements);
}
