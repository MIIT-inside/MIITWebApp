package com.example.BackendMIIT.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pass_points")
@Data
public class PassPoint extends BaseEntity {

    private int min;
    private int avg;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private Direction direction;
}
