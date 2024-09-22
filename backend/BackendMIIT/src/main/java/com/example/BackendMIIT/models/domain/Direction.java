package com.example.BackendMIIT.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "directions")
@Data
public class Direction extends BaseEntity {

    private String name;
    private int minPoints;

    @OneToMany(mappedBy = "direction")
    private List<Profile> profiles;

    @OneToMany(mappedBy = "direction")
    private List<Exam> exams;
}
