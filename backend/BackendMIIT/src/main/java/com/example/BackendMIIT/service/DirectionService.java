package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.dto.DirectionDto;
import org.jsoup.nodes.Element;

import java.util.List;

public interface DirectionService {

    void parseDirections(String url);

    void saveDirection(List<Element> elements);

    DirectionDto getDirectionByName(String name);

    DirectionDto getDirectionByCode(String code);


    List<DirectionDto> getDirections();
}
