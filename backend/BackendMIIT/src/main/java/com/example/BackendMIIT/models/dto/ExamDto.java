package com.example.BackendMIIT.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExamDto {

    @JsonProperty("name")
    private String name;
}
