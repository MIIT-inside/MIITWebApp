package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.service.MinioService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/miit/images")
public class MinioController {

	private final MinioService minioService;

	public MinioController(MinioService minioService) {
		this.minioService = minioService;
	}

	@PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> uploadImage(@RequestParam String name, @RequestPart MultipartFile image) {
		String imageUrl = minioService.uploadImage(image, name);

		return ResponseEntity.ok(imageUrl);
	}
}
