package com.example.BackendMIIT.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "exams")
@Data
public class Exam extends BaseEntity {

    private String name;
}
