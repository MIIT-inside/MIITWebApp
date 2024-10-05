package com.example.BackendMIIT.service;

import com.example.BackendMIIT.model.domain.Direction;
import org.json.JSONObject;

import java.util.List;

public interface PassPointService {
    void parsePoints(String uri);
    void savePoints(List<JSONObject> doublesValues, JSONObject groups);
}
