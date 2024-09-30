package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProfileDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("pass_point")
    private int passPoint;

    @JsonProperty("description")
    private String description;
}
