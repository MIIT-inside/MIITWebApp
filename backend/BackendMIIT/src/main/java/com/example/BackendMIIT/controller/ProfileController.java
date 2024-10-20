package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.dto.ProfileDto;
import com.example.BackendMIIT.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/institute")
    public ResponseEntity<List<ProfileDto>> getProfilesByInstitute(@RequestParam String institute) {
        return ResponseEntity.ok(profileService.getProfilesByInstitute(institute));
    }

    @GetMapping("/direction")
    public ResponseEntity<List<ProfileDto>> getProfilesByDirection(@RequestParam String code) {
        return ResponseEntity.ok(profileService.getProfilesByDirection(code));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfileByName(@RequestParam String name) {
        return ResponseEntity.ok(profileService.getProfileByName(name));
    }
}
