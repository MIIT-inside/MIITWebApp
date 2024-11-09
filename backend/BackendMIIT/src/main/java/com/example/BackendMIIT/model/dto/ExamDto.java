package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExamDto {

    @JsonProperty("subjectName")
    private String subjectName;
}
