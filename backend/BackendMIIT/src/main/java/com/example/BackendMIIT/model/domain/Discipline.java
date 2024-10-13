package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "disciplines")
@Data
public class Discipline extends BaseEntity {

    private String name;
    private String attestation;

    @OneToMany(mappedBy = "discipline")
    private List<Lesson> lessons;
}
