package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SemesterDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("disciplines")
    private List<DisciplineDto> disciplines;
}
