package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.service.PassPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/miit/points")
public class PassPointController {

    @Autowired
    private PassPointService passPointService;

    @PostMapping("/parse")
    public ResponseEntity<String> parsePassPoints(@RequestParam String uri) {
        passPointService.savePoints(uri);

        return ResponseEntity.ok("Points parsed successfully");
    }
}
