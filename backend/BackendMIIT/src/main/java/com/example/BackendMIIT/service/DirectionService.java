package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.dto.DirectionDto;
import com.example.BackendMIIT.model.dto.DirectionWithProfilesDto;
import org.jsoup.nodes.Element;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DirectionService {

    void parseDirections(String url);

    void saveDirection(List<Element> elements);

    DirectionDto getDirectionByName(String name);

    DirectionDto getDirectionByCode(String code);

    List<DirectionDto> getDirections();

    Page<DirectionWithProfilesDto> getSortedDirections(String typeOfPassPoints, int page, int size);
}

