package com.example.BackendMIIT.models.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profiles")
@Data
public class Profile extends BaseEntity {

    private String name;
    private int passPoint;
    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
