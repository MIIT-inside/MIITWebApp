package com.example.BackendMIIT.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directions")
@Data
@ToString
public class Direction extends BaseEntity {

    private String name;
    private int minPoints;

    @OneToMany(mappedBy = "direction")
    private List<Profile> profiles = new ArrayList<>();
}
