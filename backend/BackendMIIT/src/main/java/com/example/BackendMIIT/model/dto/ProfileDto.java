package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("form")
    private String form;

    @JsonProperty("level")
    private String level;

    @JsonProperty("description")
    private String description;

    @JsonProperty("institute")
    private String institute;

    @JsonProperty("abbreviation")
    private String abbreviation;

    @JsonProperty("image_url")
    private String imageUrl;
}
