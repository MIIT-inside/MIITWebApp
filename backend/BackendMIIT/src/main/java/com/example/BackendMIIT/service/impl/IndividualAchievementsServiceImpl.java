package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.IndividualAchievements;
import com.example.BackendMIIT.parser.IndividualAchievementsParser;
import com.example.BackendMIIT.repositories.IndividualAchievementsRepository;
import com.example.BackendMIIT.service.IndividualAchievementsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualAchievementsServiceImpl implements IndividualAchievementsService {

    private final IndividualAchievementsRepository individualAchievementsRepository;
    private final IndividualAchievementsParser parser;

    public IndividualAchievementsServiceImpl(IndividualAchievementsRepository individualAchievementsRepository,
                                             IndividualAchievementsParser parser) {
        this.individualAchievementsRepository = individualAchievementsRepository;
        this.parser = parser;
    }

    @Override
    public void parseAndSaveAchievements() {
         individualAchievementsRepository.saveAll(parser.parse());
    }

    @Override
    public List<IndividualAchievements> getAllIndividualAchievements() {
        return individualAchievementsRepository.findAll();
    }
}
