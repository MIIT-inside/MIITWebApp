package com.example.BackendMIIT.service;

import org.jsoup.nodes.Element;

import java.util.List;

public interface DirectionService {

    void parseDirections(String url);

    String saveDirection(List<Element> elements, String previousDirection);
}
