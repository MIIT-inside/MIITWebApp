package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.dto.DirectionDto;
import com.example.BackendMIIT.model.dto.DirectionWithProfilesDto;
import com.example.BackendMIIT.model.dto.SimplifiedDirectionsWithProfilesDto;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;

public interface DirectionService {

    void parseDirections(String url);

    void saveDirection(List<Element> elements);

    DirectionDto getDirectionByName(String name);

    DirectionDto getDirectionByCode(String code);

    List<DirectionDto> getDirections();

    List<DirectionWithProfilesDto> getSortedDirectionsByCategory(String ppType, String category, int page, int size);

    List<SimplifiedDirectionsWithProfilesDto> getDirectionsByExamsAndPp(HashMap<String, Integer> examsAndPp);
}
