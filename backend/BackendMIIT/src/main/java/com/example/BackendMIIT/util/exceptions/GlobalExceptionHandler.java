package com.example.BackendMIIT.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ImageUploadException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleImageUpload(ImageUploadException e) {
		return e.getMessage();
	}
}
