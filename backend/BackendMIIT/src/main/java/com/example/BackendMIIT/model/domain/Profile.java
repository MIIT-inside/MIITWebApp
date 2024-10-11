package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "profiles")
@Data
public class Profile extends BaseEntity {

    private String name;
    private String form;
    private String level;
    private String description;
    private String institute;
    private String abbreviation;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
}
