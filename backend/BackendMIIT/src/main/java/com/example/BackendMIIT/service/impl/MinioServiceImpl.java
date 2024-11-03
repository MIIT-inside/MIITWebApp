package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.repository.DirectionRepository;
import com.example.BackendMIIT.repository.ProfileRepository;
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

	private final ProfileRepository profileRepository;
	private final DirectionRepository directionRepository;
	private final MinioClient minioClient;

	@Value("${minio.bucket}")
	private String bucketName;

	public MinioServiceImpl(ProfileRepository profileRepository, DirectionRepository directionRepository, MinioClient minioClient) {
		this.profileRepository = profileRepository;
		this.directionRepository = directionRepository;
		this.minioClient = minioClient;
	}

	@Override
	public String uploadDirectionImage(MultipartFile file, String direction) {

		String fileName = bucketExists(file);
		String imageUrl;

		try (InputStream inputStream = file.getInputStream()) {
			imageUrl = saveDirectionImage(inputStream, fileName, direction);
		}
		catch (Exception e) {
			throw new ImageUploadException(("Image upload failed" + e.getMessage()));
		}

		return imageUrl;
	}

	private String bucketExists(MultipartFile file) {
		try {
			createBucket();
		}
		catch (Exception e) {
			throw new ImageUploadException("Image upload failed" + e.getMessage());
		}

		if (file.isEmpty() || file.getOriginalFilename() == null) {
			throw new ImageUploadException("Image must have name");
		}

		return generateFileName(file);
	}

	private String saveProfileImage(InputStream inputStream, String fileName, String direction) {
		Profile profile = profileRepository.findByName(direction)
				.orElseThrow(() -> new EntityNotFoundException("Profile doesn't exist"));
		String imageUrl = uploadImage(inputStream, fileName);

		profile.setImageUrl(imageUrl);
		profileRepository.save(profile);

		return imageUrl;
	}

	@Override
	public String uploadProfileImage(MultipartFile file, String profile) {

		String fileName = bucketExists(file);
		String imageUrl;

		try (InputStream inputStream = file.getInputStream()) {
			imageUrl = saveProfileImage(inputStream, fileName, profile);
		}
		catch (Exception e) {
			throw new ImageUploadException(("Image upload failed" + e.getMessage()));
		}

		return imageUrl;
	}

	@SneakyThrows
	private String uploadImage(InputStream inputStream, String fileName) {
		minioClient.putObject(
				PutObjectArgs.builder()
						.stream(inputStream, inputStream.available(), -1)
						.bucket(bucketName)
						.object(fileName)
						.build()
		);

		return "localhost:9001/" + fileName;
	}

	@SneakyThrows
	private String saveDirectionImage(InputStream inputStream, String fileName, String name) {

		Direction direction = directionRepository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException("Profile doesn't exist"));

		String imageUrl = uploadImage(inputStream, fileName);

		direction.setImageUrl(imageUrl);
		directionRepository.save(direction);

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
