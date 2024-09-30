package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.dto.DirectionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectionMapper {

    Direction dtoToDirection(DirectionDto directionDto);

    DirectionDto directionToDto(Direction direction);
}
