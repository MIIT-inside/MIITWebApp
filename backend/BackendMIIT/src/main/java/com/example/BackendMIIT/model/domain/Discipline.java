package com.example.BackendMIIT.model.domain;

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

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
}
