package com.example.BackendMIIT.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profiles")
@Data
public class Profile extends BaseEntity {

    private String name;
    private String form;
    private String description;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

    @OneToOne
    private PassPoint passPoint;
}
