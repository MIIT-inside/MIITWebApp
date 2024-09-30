package com.example.BackendMIIT.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DirectionDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("pass_point")
    private int passPoint;
}