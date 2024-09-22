package com.example.BackendMIIT.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "exams")
public class Exam extends BaseEntity {

    private String name;

    protected Exam() {}

    public Exam(String name) {
        this.name = name;
    }

    @Column(name = "exam_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
