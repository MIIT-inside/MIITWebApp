package com.example.BackendMIIT.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
public class Department extends BaseEntity implements Serializable {

	private String name;

	@JsonManagedReference
	@OneToMany(mappedBy = "department")
	private List<Educator> educator;
}
