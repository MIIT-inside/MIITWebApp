package com.example.BackendMIIT.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
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
    @ManyToMany
    @JoinTable(
            name = "direction_exam",
            joinColumns = @JoinColumn(name = "direction_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private Set<Exam> exams;

    @JsonManagedReference
    @OneToMany(mappedBy = "direction")
    private List<PassPoint> passPoints;
}
