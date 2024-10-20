package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.dto.EducationPlanDto;

public interface EducationPlanService {

    void parseAndSaveEducationPlans();

    EducationPlanDto getPlansByProfile(String name);
}
