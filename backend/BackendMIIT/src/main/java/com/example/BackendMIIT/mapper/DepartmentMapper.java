package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.models.domain.Department;
import com.example.BackendMIIT.models.dto.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department dtoToDepartment(DepartmentDto departmentDto);

    DepartmentDto departmentToDto(Department department);
}
