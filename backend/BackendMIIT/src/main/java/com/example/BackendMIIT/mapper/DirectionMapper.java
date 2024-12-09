package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.PassPoint;
import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.model.dto.DirectionDto;
import com.example.BackendMIIT.model.dto.DirectionWithProfilesDto;
import com.example.BackendMIIT.model.dto.PassPointDto;
import com.example.BackendMIIT.model.dto.ProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectionMapper {

    Direction dtoToDirection(DirectionDto directionDto);

    DirectionDto directionToDto(Direction direction);

    List<DirectionDto> directionToDirectionDto(List<Direction> directions);

    @Mapping(target = "passPoints", source = "passPoints")
    @Mapping(target = "profiles", source = "profiles")
    DirectionWithProfilesDto directionToWithProfilesDto(Direction direction);

    List<DirectionWithProfilesDto> directionsToWithProfilesDto(List<Direction> directions);

    @Mapping(target = "category", source = "category.value")
    PassPointDto passPointToPassPointDto(PassPoint passPoint);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "form", ignore = true)
    @Mapping(target = "level", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "institute", ignore = true)
    @Mapping(target = "abbreviation", ignore = true)
    ProfileDto profileToProfileDto(Profile profile);
}
