package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.dto.ProfileDto;
import com.example.BackendMIIT.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/miit/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/parse")
    public ResponseEntity<String> parseProfiles(@RequestParam String url) {
        profileService.parseProfile(url);

        return ResponseEntity.ok("Profiles successfully parsed");
    }

    @GetMapping("/")
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/pass")
    public ResponseEntity<List<ProfileDto>> getProfilesByPassPoint(@PathVariable int passPoint) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfileByName(@PathVariable String name) {
        return ResponseEntity.ok(new ProfileDto());
    }
}
