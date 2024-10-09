package com.example.BackendMIIT.service;

import org.jsoup.nodes.Element;

import java.util.List;

public interface DirectionService {

    void parseDirections(String url);

    void saveDirection(List<Element> elements);
}
