package com.example.BackendMIIT.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

	String uploadProfileImage(MultipartFile file, String profile);

	String uploadDirectionImage(MultipartFile file, String direction);
}
