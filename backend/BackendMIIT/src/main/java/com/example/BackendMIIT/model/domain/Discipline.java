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
@Table(name = "disciplines")
@Data
public class Discipline extends BaseEntity implements Serializable {

    private String name;
    private String attestation;

    @JsonManagedReference
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
}
