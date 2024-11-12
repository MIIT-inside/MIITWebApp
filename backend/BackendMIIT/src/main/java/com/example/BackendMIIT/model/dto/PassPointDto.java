package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PassPointDto {

    @JsonProperty("min")
    private int min;

    @JsonProperty("max")
    private int avg;

    @JsonProperty("category")
    private String category;
}
