package com.example.BackendMIIT.repository;

import com.example.BackendMIIT.model.domain.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, UUID> {
}
