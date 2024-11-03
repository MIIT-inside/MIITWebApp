package com.example.BackendMIIT.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

	String uploadImage(MultipartFile file, String profile);
}
