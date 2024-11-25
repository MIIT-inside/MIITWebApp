package com.example.BackendMIIT.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "directions")
@Data
public class Direction extends BaseEntity implements Serializable {

    private String code;
    private String name;
    private String level;
    private String form;
    private String imageUrl;

    @JsonManagedReference
    @OneToMany(mappedBy = "direction")
    private List<Profile> profiles;

    @JsonManagedReference
    @OneToMany(mappedBy = "direction")
    private List<Exam> exams;

    @JsonManagedReference
    @OneToMany(mappedBy = "direction")
    private List<PassPoint> passPoints;
}
