package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.model.domain.Exam;
import com.example.BackendMIIT.model.dto.ExamDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    Exam dtoToExam(ExamDto examDto);

    ExamDto examToDto(Exam exam);
}
