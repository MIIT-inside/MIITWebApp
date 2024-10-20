package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EducationPlanDto {

    @JsonProperty("profile_name")
    private String profileName;


    private String pdfLink;
}
