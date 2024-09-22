package com.example.BackendMIIT.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "exams")
@Data
@ToString
public class Exam extends BaseEntity {

    private String name;
}
