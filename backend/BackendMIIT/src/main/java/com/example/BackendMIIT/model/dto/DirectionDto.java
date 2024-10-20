package com.example.BackendMIIT.model.dto;

import com.example.BackendMIIT.model.domain.PassPoint;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DirectionDto {

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("level")
    private String level;

    @JsonProperty("form")
    private String form;

    @JsonProperty("pass_points")
    private List<PassPoint> passPoints;
}
