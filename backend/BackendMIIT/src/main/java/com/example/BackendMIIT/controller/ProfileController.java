package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.dto.ProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/miit/profiles")
public class ProfileController {

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getProfilesByPassPoint(@PathVariable int passPoint) {
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @GetMapping
    public ResponseEntity<ProfileDto> getProfileByName(@PathVariable String name) {
        return ResponseEntity.ok().body(new ProfileDto());
    }
}
