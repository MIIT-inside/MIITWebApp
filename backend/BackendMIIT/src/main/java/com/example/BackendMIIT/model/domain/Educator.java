package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "educators")
@Data
public class Educator extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "department_id")
    public Department department;
    private String name;
    private String surname;
    private String patronymic;
}
