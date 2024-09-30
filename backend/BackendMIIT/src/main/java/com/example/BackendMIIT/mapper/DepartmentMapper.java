package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.model.domain.Department;
import com.example.BackendMIIT.model.dto.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department dtoToDepartment(DepartmentDto departmentDto);

    DepartmentDto departmentToDto(Department department);
}
