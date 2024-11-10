package com.example.BackendMIIT.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DirectionExamPointsDto {

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("exams")
    private List<ExamPointsDto> exams;

    public static class ExamPointsDto {

        @JsonProperty("subject")
        private String subject;

        @JsonProperty("minPoints")
        private int minPoints;
    }
}
