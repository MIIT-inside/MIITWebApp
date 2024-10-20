package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.model.domain.Semester;
import com.example.BackendMIIT.model.dto.SemesterDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SemesterMapper {

    List<SemesterDto> semestersToDto(List<Semester> semesters);
}
