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

    private String code;
    private String name;
    private String level;
    private String form;

    @OneToMany(mappedBy = "direction")
    private List<Profile> profiles;

    @OneToMany(mappedBy = "direction")
    private List<Exam> exams;

    @OneToMany(mappedBy = "direction")
    private List<PassPoint> passPoints;
}
