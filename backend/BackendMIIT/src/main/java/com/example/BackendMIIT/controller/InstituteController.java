package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/miit/institutes")
@RequiredArgsConstructor
public class InstituteController {

	private final InstituteService instituteService;

	@PostMapping("/parse/")
	public ResponseEntity<String> parseInstitutes() {

		return ResponseEntity.ok(instituteService.parseInstitutes());
	}

}
