package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LessonDto {

    @JsonProperty("lab")
    private String labWork;

    @JsonProperty("lecture")
    private String lecture;

    @JsonProperty("practice")
    private String practice;
}
