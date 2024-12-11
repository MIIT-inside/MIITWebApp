package com.example.BackendMIIT.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exams")
@Data
public class Exam extends BaseEntity implements Serializable {

    private String subjectName;

    @JsonBackReference
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private Set<DirectionExamPoints> directionExamPoints = new HashSet<>();
}
