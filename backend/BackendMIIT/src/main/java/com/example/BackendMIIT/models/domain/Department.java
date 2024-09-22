package com.example.BackendMIIT.models.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    private String name;
    private String teacher;

    protected Department() {}

    public Department(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    @Column(name = "department_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "teachers")
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
