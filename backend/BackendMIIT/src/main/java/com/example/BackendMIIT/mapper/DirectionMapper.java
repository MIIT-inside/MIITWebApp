package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.dto.DirectionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectionMapper {

    Direction dtoToDirection(DirectionDto directionDto);

    DirectionDto directionToDto(Direction direction);

    List<DirectionDto> directionsToDirectionDto(List<Direction> directions);
}
