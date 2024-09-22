package com.example.BackendMIIT.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    private String name;
    private List<String> teachers;

    protected Department() {}

    public Department(String name, List<String> teachers) {
        this.name = name;
        this.teachers = teachers;
    }

    @Column(name = "department_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "teachers")
    public List<String> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<String> teachers) {
        this.teachers = teachers;
    }
}
