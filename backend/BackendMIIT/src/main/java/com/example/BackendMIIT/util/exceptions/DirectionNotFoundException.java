package com.example.BackendMIIT.util.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class DirectionNotFoundException extends EntityNotFoundException {

    public DirectionNotFoundException(String message) {
        super(message);
    }

    public DirectionNotFoundException() {
        super("Направление не найдено");
    }
}
