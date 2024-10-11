package com.example.BackendMIIT.service;

import org.json.JSONObject;
import org.jsoup.nodes.Element;

import java.util.List;

public interface ProfileService {

    void parseProfile(String url);

    //void saveProfile(List<String> directionLinks, String directionCode);
}
