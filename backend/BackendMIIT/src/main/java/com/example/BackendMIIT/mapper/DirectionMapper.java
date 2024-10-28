package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.dto.DirectionDto;
import com.example.BackendMIIT.model.dto.DirectionWithProfilesDto;
import com.example.BackendMIIT.model.dto.PassPointDto;
import com.example.BackendMIIT.model.dto.ProfileDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DirectionMapper {

    Direction dtoToDirection(DirectionDto directionDto);

    DirectionDto directionToDto(Direction direction);

    List<DirectionDto> directionToDirectionDto(List<Direction> directions);

    default DirectionWithProfilesDto directionToWithProfilesDto(Direction direction) {
        DirectionWithProfilesDto directionDto = new DirectionWithProfilesDto();

        directionDto.setCode(direction.getCode());
        directionDto.setName(direction.getName());
        directionDto.setLevel(direction.getLevel());
        directionDto.setForm(direction.getForm());

        List<PassPointDto> passPointDtos = direction.getPassPoints().stream()
                .map(passPoint -> {
                    PassPointDto passPointDto = new PassPointDto();
                    passPointDto.setMin(passPoint.getMin());
                    passPointDto.setAvg(passPoint.getAvg());
                    passPointDto.setCategory(passPoint.getCategory() != null ?
                            passPoint.getCategory().getValue() : null);
                    return passPointDto;
                })
                .collect(Collectors.toList());

        directionDto.setPassPoints(passPointDtos);

        List<ProfileDto> profileDtos = direction.getProfiles().stream()
                .map(profile -> {
                    ProfileDto profileDto = new ProfileDto();
                    profileDto.setName(profile.getName()); // Только имя профиля
                    return profileDto;
                })
                .collect(Collectors.toList());

        directionDto.setProfiles(profileDtos);

        return directionDto;
    }

    default List<DirectionWithProfilesDto> directionsToWithProfilesDto(List<Direction> directions) {
        return directions.stream()
                .map(this::directionToWithProfilesDto)
                .collect(Collectors.toList());
    }
}
