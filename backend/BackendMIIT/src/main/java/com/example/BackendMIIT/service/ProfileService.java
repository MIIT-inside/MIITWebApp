package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.dto.ProfileDto;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

import java.util.List;

public interface ProfileService {

    void parseProfile(String url);

    void saveProfile(List<String> properties);

    List<ProfileDto> getProfilesByInstitute(String institute);

    List<ProfileDto> getAllProfiles();

    ProfileDto getProfileByName(String name);

    List<ProfileDto> getProfilesByDirection(String code);
}
