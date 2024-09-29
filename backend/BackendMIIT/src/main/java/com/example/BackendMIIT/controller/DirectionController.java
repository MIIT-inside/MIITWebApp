package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.dto.DirectionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/miit/directions")
public class DirectionController {

    @GetMapping
    public ResponseEntity<List<DirectionDto>> getAllDirections() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping
    public ResponseEntity<List<DirectionDto>> getDirectionsByPassPoint(@PathVariable int passPoint) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping
    public ResponseEntity<DirectionDto> getDirectionByName(@RequestParam String directionName) {
        return ResponseEntity.ok(new DirectionDto());
    }
}
