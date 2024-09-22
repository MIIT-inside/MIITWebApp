package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.models.domain.Direction;
import com.example.BackendMIIT.models.dto.DirectionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectionMapper {
    Direction dtoToDirection(DirectionDto directionDto);
    DirectionDto departmentToDto(Direction direction);
}
