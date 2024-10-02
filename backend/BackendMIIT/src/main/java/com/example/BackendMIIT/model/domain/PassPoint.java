package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pass_points")
@Data
public class PassPoint extends BaseEntity{

    private int main;
    private int target;
    private int special;
    private int separate;
    private int contract;

    @OneToOne
    private Direction direction;

    @OneToOne
    private PassPoint passPoint;
}
