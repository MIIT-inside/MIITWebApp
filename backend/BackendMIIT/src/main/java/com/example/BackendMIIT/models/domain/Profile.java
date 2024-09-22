package com.example.BackendMIIT.models.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profiles")
@Data
public class Profile extends BaseEntity {

    private String name;
    private int minPoints;
    private String description;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
}
