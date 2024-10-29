package com.example.BackendMIIT.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "exams")
@Data
public class Exam extends BaseEntity implements Serializable {

    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
}
