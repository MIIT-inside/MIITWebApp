package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.parser.EducationPlanParser;
import com.example.BackendMIIT.repositories.ProfileRepository;
import com.example.BackendMIIT.service.EducationPlanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationPlanServiceImpl implements EducationPlanService {

    private final ProfileRepository profileRepository;
    private final EducationPlanParser parser;

    public EducationPlanServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
        this.parser = new EducationPlanParser();
    }

    @Override
    @Transactional
    public void parseAndSaveEducationPlans() {
        // TODO
    }
}
