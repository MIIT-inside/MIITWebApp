package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "individual_achievements")
@Data
public class IndividualAchievements extends BaseEntity {

    private int countPoints;
    private String description;
}
