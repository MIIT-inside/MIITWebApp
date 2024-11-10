package com.example.BackendMIIT.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DirectionNotFoundException.class)
    public ResponseEntity<String> handleDirectionNotFoundException(DirectionNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Введите название направления");
    }
}
