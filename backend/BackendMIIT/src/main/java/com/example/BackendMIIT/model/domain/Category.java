package com.example.BackendMIIT.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {

    MAIN("Общий конкурс"),
    TARGET("Целевая квота"),
    SPECIAL("Особая квота"),
    SEPARATE("Отдельная квота"),
    CONTRACT("Договорная основа");

    private final String value;
}
