package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.DepartmentRequest;
import com.example.employeemanagement.model.DepartmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class DepartmentMapper {
    public static final DepartmentMapper INSTANCE=Mappers.getMapper(DepartmentMapper.class);

    @Mapping(source = "ids",target = "id")
    public  abstract Position integerToPosition(Integer ids);

    @Mapping(source = "positionIds", target = "positions")
    public abstract Department requestToEntity(DepartmentRequest request);
    public abstract DepartmentResponse entityToResponse(Department department);

    public abstract List<DepartmentResponse> entityListToResponseList(List<Department> departments);
    public  abstract void updateEntity(@MappingTarget Department department, DepartmentRequest request);

}
