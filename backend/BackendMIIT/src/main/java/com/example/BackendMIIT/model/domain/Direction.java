package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "directions")
@Data
public class Direction extends BaseEntity {

    private String name;
    private int passPoint;

    @OneToMany(mappedBy = "direction")
    private List<Profile> profiles;

    @OneToMany(mappedBy = "direction")
    private List<Exam> exams;
}
