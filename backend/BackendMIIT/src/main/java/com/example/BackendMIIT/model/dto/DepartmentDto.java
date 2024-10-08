package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepartmentDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("teacher")
    private String teacher;
}
