package com.example.BackendMIIT.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class SimplifiedDirectionsWithProfilesDto {

    private String code;
    private String name;
    private String level;
    private String form;
    private Integer passPoints;
    private List<ProfileDto> profiles;
}
