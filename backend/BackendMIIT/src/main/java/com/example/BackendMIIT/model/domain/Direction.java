package com.example.BackendMIIT.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "directions")
@Data
public class Direction extends BaseEntity implements Serializable {

    private String code;
    private String name;
    private String level;
    private String form;

    @JsonManagedReference
    @OneToMany(mappedBy = "direction")
    private List<Profile> profiles;

    @JsonManagedReference
    @OneToMany(mappedBy = "direction")
    private List<PassPoint> passPoints;

    @OneToMany(mappedBy = "direction", cascade = CascadeType.ALL)
    private Set<DirectionExamPoints> directionExamScores = new HashSet<>();
}
