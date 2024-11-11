package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PassPointDto {

    @JsonProperty("min")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int min;

    @JsonProperty("avg")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int avg;

    @JsonProperty("category")
    private String category;
}
