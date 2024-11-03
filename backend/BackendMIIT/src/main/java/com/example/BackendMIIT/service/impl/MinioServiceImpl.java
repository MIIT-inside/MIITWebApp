package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.service.MinioService;
import com.example.BackendMIIT.util.exceptions.ImageUploadException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioServiceImpl implements MinioService {

	private final MinioClient minioClient;

	@Value("${minio.bucket}")
	private String bucketName;

	@Value("${minio.url}")
	private String url;

	public MinioServiceImpl(MinioClient minioClient) {
		this.minioClient = minioClient;
	}

	@Override
	public String uploadImage(MultipartFile file, String profile) {
		try {
			createBucket();
		}
		catch (Exception e) {
			throw new ImageUploadException("Image upload failed" + e.getMessage());
		}

		if (file.isEmpty() || file.getOriginalFilename() == null) {
			throw new ImageUploadException("Image must have name");
		}

		String fileName = generateFileName(file);
		String imageUrl;
		try (InputStream inputStream = file.getInputStream()) {
			imageUrl = saveImage(inputStream, fileName, profile);
		}
		catch (Exception e) {
			throw new ImageUploadException(("Image upload failed" + e.getMessage()));
		}

		return imageUrl;
	}

	@SneakyThrows
	private String saveImage(InputStream inputStream, String fileName, String profileName) {
		minioClient.putObject(
				PutObjectArgs.builder()
						.stream(inputStream, inputStream.available(), -1)
						.bucket(bucketName)
						.object(fileName)
						.build()
		);

		Profile profile = profileRepository.findByName(profileName)
				.orElseThrow(() -> new EntityNotFoundException("Profile doesn't exist"));
		String imageUrl = url + "/" + fileName;

		profile.setImageUrl(imageUrl);
		profileRepository.save(profile);

		return imageUrl;
	}

	private String generateFileName(MultipartFile file) {
		String extension = getExtension(file);
		return UUID.randomUUID() + "." + extension;
	}

	private String getExtension(MultipartFile file) {
		return file.getOriginalFilename().
				substring(file.getOriginalFilename().lastIndexOf(".") + 1);
	}

	@SneakyThrows
	private void createBucket() {
		boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
				.bucket(bucketName)
				.build());
		if (!found) {
			minioClient.makeBucket(MakeBucketArgs.builder()
					.bucket(bucketName)
					.build());
		}
	}
}
