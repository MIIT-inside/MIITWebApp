package com.example.BackendMIIT.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directions")
public class Direction extends BaseEntity {

    private String name;
    private int minPoints;
    private List<Profile> profiles = new ArrayList<>();

    public Direction() {}

    public Direction(String name, int minPoints, List<Profile> profiles) {
        this.name = name;
        this.minPoints = minPoints;
        this.profiles = profiles;
    }

    @Column(name = "direction_name")
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
        if (minPoints < 0) { return; } // TODO: replace with the actual exception
        this.minPoints = minPoints;
    }

    @OneToMany(mappedBy = "direction")
    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
