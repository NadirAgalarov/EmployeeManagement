package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.model.EmployeeRequest;
import com.example.employeemanagement.model.EmployeeResponse;
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
public abstract class EmployeeMapper {
    public  static final EmployeeMapper INSTANCE= Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "department",target = "departmentResponse")
    @Mapping(source = "position",target = "positionResponse")
    public abstract EmployeeResponse entityToResponse(Employee employee);
    @Mapping(target = "position.id",source = "positionId")
    @Mapping(target = "department.id",source = "departmentId")
    public abstract Employee requestToEntity(EmployeeRequest request);

    public abstract List<EmployeeResponse> entityListToResponseList(List<Employee> employees);
    @Mapping(target = "position.id",source = "positionId")
    @Mapping(target = "department.id",source = "departmentId")
    @Mapping(target = "department.position.id" , ignore = true)
    public abstract void updateEntity(@MappingTarget Employee employee,EmployeeRequest request);


}
