package com.example.BackendMIIT.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// TODO: replace TypeEntity with an Educator type when the Educator entity appears
public interface EducatorRepository<TypeEntity, ID> extends BaseRepository<TypeEntity, ID> {
    List<TypeEntity> findByDepartmentName(String departmentName);
}
