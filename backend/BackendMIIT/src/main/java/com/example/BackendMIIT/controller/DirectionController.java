package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.model.dto.DirectionDto;
import com.example.BackendMIIT.model.dto.DirectionWithProfilesDto;
import com.example.BackendMIIT.model.dto.SimplifiedDirectionsWithProfilesDto;
import com.example.BackendMIIT.service.DirectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "api/miit/directions")
public class DirectionController {

    private final DirectionService directionService;

    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }

    @GetMapping("/direction/{code}")
    public ResponseEntity<DirectionDto> getDirectionByCode(@PathVariable String code) {
        return ResponseEntity.ok(directionService.getDirectionByCode(code));
    }

    @PostMapping("/parse")
    public ResponseEntity<String> parseDirections(@RequestParam String url) {
        directionService.parseDirections(url);

        return ResponseEntity.ok().body("Directions successfully parsed");
    }

    @GetMapping("/")
    public ResponseEntity<List<DirectionDto>> getAllDirections() {
        return ResponseEntity.ok(directionService.getDirections());
    }

    @GetMapping("/{passPoint}")
    public ResponseEntity<List<DirectionDto>> getDirectionsByPassPoint(@PathVariable int passPoint) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/direction")
    public ResponseEntity<DirectionDto> getDirectionByName(@RequestParam String name) {
        return ResponseEntity.ok(directionService.getDirectionByName(name));
    }

    @GetMapping("/sorted")
    public List<DirectionWithProfilesDto> getSortedDirections(
            @RequestParam(defaultValue = "min") String ppType,
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return directionService.getSortedDirectionsByCategory(ppType, category, page, size);
    }

    @GetMapping("/by-pp")
    public List<SimplifiedDirectionsWithProfilesDto> getDirectionsByTotalPp(@RequestParam Map<String, String> examAndPp) {
        HashMap<String, Integer> examsAndPpMap = new HashMap<>();
        for (Map.Entry<String, String> entry : examAndPp.entrySet()) {
            examsAndPpMap.put(entry.getKey(), Integer.parseInt(entry.getValue()));
        }

        return directionService.getDirectionsByExamsAndPp(examsAndPpMap);
    }
}
