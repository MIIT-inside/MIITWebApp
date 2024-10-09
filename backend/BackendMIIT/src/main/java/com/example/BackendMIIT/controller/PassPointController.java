package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.service.PassPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/miit/points")
public class PassPointController {

    private final PassPointService passPointService;

    public PassPointController(PassPointService passPointService) {
        this.passPointService = passPointService;
    }

    @PostMapping("/parse")
    public ResponseEntity<String> parsePassPoints(@RequestParam String uri) {
        passPointService.parsePoints(uri);

        return ResponseEntity.ok("Points parsed successfully");
    }
}
