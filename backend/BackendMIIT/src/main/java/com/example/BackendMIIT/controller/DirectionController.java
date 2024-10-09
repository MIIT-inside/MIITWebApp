package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.dto.DirectionDto;
import com.example.BackendMIIT.service.DirectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/miit/directions")
public class DirectionController {

    private final DirectionService directionService;

    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }

    @PostMapping("/parse")
    public ResponseEntity<String> parseDirections(@RequestParam String url) {
        directionService.parseDirections(url);

        return ResponseEntity.ok().body("Directions successfully parsed");
    }

    @GetMapping("/")
    public ResponseEntity<List<DirectionDto>> getAllDirections() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{passPoint}")
    public ResponseEntity<List<DirectionDto>> getDirectionsByPassPoint(@PathVariable int passPoint) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/direction")
    public ResponseEntity<DirectionDto> getDirectionByName(@RequestParam String directionName) {
        return ResponseEntity.ok(new DirectionDto());
    }
}
