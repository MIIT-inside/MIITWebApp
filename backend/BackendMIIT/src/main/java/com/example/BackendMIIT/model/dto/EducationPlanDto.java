package com.example.BackendMIIT.model.dto;

import com.example.BackendMIIT.model.domain.Semester;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EducationPlanDto {

    @JsonProperty("profile_name")
    private String profileName;

    @JsonProperty("education_plan")
    private List<SemesterDto> semesters;
}
