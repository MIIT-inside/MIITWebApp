package com.example.BackendMIIT.service;

import org.jsoup.nodes.Element;

import java.util.List;

public interface ProfileService {

    void parseProfile(String url);

    void saveProfile(List<Element> elements);
}
