package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

public enum Category {

    MAIN,
    TARGET,
    SPECIAL,
    SEPARATE,
    CONTRACT
}
