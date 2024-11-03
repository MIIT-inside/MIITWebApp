package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.dto.ProfileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfileService {

    void parseProfile(String url);

    void saveProfile(List<String> properties);

    List<ProfileDto> getProfilesByInstituteName(String name);

    List<ProfileDto> getAllProfiles();

    ProfileDto getProfileByName(String name);

    List<ProfileDto> getProfilesByDirectionCode(String code);
}
