package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "individual_achievements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualAchievements extends BaseEntity {

    private String description;
    private String countPoints;
}
