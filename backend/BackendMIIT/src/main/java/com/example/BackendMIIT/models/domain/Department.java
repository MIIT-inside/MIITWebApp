package com.example.BackendMIIT.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "departments")
@Data
@ToString
public class Department extends BaseEntity {

    private String name;
    private String teacher;
}
