package com.example.BackendMIIT.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "pass_points")
@Data
public class PassPoint extends BaseEntity implements Serializable {

    private int min;
    private int avg;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
}
