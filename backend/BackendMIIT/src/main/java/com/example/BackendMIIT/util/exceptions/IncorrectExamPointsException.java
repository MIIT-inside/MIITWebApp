package com.example.BackendMIIT.util.exceptions;

public class IncorrectExamPointsException extends IllegalArgumentException {

    public IncorrectExamPointsException() {
        super("Неверно указано количество баллов");
    }

    public IncorrectExamPointsException(String message) {
        super(message);
    }
}
