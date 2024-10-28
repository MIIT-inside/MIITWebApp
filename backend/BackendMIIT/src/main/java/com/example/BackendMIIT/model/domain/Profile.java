package com.example.BackendMIIT.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "profiles")
@Data
public class Profile extends BaseEntity implements Serializable {

    private String name;
    private String form;
    private String level;
    private String description;
    private String institute;
    private String abbreviation;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

    @OneToMany(mappedBy = "profile")
    private List<Semester> semesters;
}
