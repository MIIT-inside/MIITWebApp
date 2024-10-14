package com.example.BackendMIIT.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "semesters")
@Data
public class Semester extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
    private List<Discipline> disciplines;
}
