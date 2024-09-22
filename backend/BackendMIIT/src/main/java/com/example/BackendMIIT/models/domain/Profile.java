package com.example.BackendMIIT.models.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "profiles")
@Data
@ToString
public class Profile extends BaseEntity {

    private String name;
    private int minPoints;
    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
