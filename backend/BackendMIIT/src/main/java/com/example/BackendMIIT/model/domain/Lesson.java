package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lessons")
@Data
public class Lesson extends BaseEntity implements Serializable {

    private String laboratoryWork;
    private String lecture;
    private String practice;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;
}
