package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "departments")
@Data
public class Department extends BaseEntity {

    private String name;
    @OneToMany(mappedBy = "department")
    private List<Educator> educator;
}
