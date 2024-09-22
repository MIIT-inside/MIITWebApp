package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.models.domain.Profile;
import com.example.BackendMIIT.models.dto.ProfileDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile dtoToProfile(ProfileDto profileDto);

    Profile profileToDto(Profile profile);
}
