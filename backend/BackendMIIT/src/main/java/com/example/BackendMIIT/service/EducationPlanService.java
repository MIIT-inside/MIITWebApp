package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.domain.Profile;

import java.util.List;

public interface EducationPlanService {
    void parseAndSaveEducationPlans(List<Profile> profiles);
}
