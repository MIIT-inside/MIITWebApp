package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DisciplineDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("attestation")
    private String attestation;

    @JsonProperty("lessons")
    private List<LessonDto> lessons;
}
