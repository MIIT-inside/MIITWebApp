package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.model.dto.ProfileDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile dtoToProfile(ProfileDto profileDto);

    ProfileDto profileToDto(Profile profile);
}
