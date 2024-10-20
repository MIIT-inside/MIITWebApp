package com.example.BackendMIIT.mapper;

import com.example.BackendMIIT.model.domain.Category;
import com.example.BackendMIIT.model.domain.PassPoint;
import com.example.BackendMIIT.model.dto.PassPointDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassPointMapper {

    @Mapping(source = "category", target = "category", qualifiedByName = "categoryToString")
    List<PassPointDto> passPointsToDto(List<PassPoint> passPoints);

    private String categoryToString(Category category) {
        return category != null ? category.getValue() : null;
    }
}
