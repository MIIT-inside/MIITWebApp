package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProfileDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("form")
    private String form;

    @JsonProperty("level")
    private String level;

    @JsonProperty("description")
    private String description;

    @JsonProperty("abbreviation")
    private String abbreviation;
}
