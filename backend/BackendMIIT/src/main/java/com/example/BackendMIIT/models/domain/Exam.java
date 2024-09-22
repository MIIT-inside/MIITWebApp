package com.example.BackendMIIT.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "exams")
@Data
public class Exam extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
}
