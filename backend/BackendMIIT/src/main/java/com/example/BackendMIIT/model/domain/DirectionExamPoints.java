package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "direction_exam_points")
@Data
public class DirectionExamPoints extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "direction_id", nullable = false)
    private Direction direction;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    private int minPoints;
}
