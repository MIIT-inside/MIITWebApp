package com.example.BackendMIIT.service;

import org.json.JSONObject;

import java.util.List;

public interface PassPointService {
    void parsePoints(String uri);

    void savePoints(List<JSONObject> doublesValues, JSONObject groups);
}
