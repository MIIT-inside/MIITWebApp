package com.example.BackendMIIT.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "educators")
@Data
public class Educator extends BaseEntity implements Serializable {

	private String name;
	private String surname;
	private String patronymic;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "department_id")
	public Department department;
}
