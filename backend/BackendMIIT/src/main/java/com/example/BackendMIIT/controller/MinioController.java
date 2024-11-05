package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.service.MinioService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("api/miit/images")
public class MinioController {

	private final MinioService minioService;

	public MinioController(MinioService minioService) {
		this.minioService = minioService;
	}

	@PostMapping(value = "/upload/profile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> uploadProfileImage(@RequestParam String name, @RequestPart MultipartFile image) {
		String imageUrl = minioService.uploadProfileImage(image, name);

		return ResponseEntity.ok(imageUrl);
	}

	@PostMapping(value = "/upload/direction", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> uploadDirectionImage(@RequestParam String name, @RequestPart MultipartFile image) {
		String imageUrl = minioService.uploadDirectionImage(image, name);

		return ResponseEntity.ok(imageUrl);
	}
}
