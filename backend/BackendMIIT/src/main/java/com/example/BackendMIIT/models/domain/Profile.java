package com.example.BackendMIIT.models.domain;

import com.example.BackendMIIT.util.exceptions.IncorrectExamPointsException;
import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    private String name;
    private int minPoints;
    private String description;
    private Department department;

    protected Profile() {}

    public Profile(String name, int minPoints, String description, Department department) {
        this.name = name;
        this.minPoints = minPoints;
        this.description = description;
        this.department = department;
    }

    @Column(name = "profile_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "min_points")
    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        if (minPoints < 0) {
            throw new IncorrectExamPointsException();
        }

        this.minPoints = minPoints;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "department_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
