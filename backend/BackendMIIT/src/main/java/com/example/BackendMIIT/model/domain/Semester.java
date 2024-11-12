package com.example.BackendMIIT.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "semesters")
@Data
public class Semester extends BaseEntity implements Serializable {

    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @JsonManagedReference
    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
    private List<Discipline> disciplines;
}
