package com.example.BackendMIIT.repositories;

import com.example.BackendMIIT.model.domain.Educator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducatorRepository extends BaseRepository<Educator> {
    List<Educator> findByDepartmentName(String departmentName);
}
