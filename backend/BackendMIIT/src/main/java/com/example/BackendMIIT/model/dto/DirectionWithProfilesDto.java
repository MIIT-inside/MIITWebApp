package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DirectionWithProfilesDto {

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("level")
    private String level;

    @JsonProperty("form")
    private String form;

    @JsonProperty("pass_points")
    private List<PassPointDto> passPoints;

    @JsonProperty("profiles")
    private List<ProfileDto> profiles;
}

