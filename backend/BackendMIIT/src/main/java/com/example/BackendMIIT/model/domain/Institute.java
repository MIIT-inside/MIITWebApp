package com.example.BackendMIIT.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "institutes")
@Data
@NoArgsConstructor
public class Institute extends BaseEntity implements Serializable {

	@Column(name = "name")
	private String name;

	@Column(name = "abbreviation")
	private String abbreviation;

	@OneToMany
	private List<Department> departments;

	@OneToMany
	private List<Profile> profiles;
}
