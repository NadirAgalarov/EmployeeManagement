package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.model.request.EmployeeRequest;
import com.example.employeemanagement.model.response.EmployeeResponse;
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

    @Mapping(target = "position.id",source = "positionId")
    public abstract Employee requestToEntity(EmployeeRequest request);
    @Mapping(source = "position.id",target = "positionId")
    public abstract EmployeeResponse entityToResponse(Employee employee);

    public abstract List<EmployeeResponse> entityListToResponseList(List<Employee> employees);
    @Mapping(target = "position.id",source = "positionId")
    public abstract void updateEntity(@MappingTarget Employee employee,EmployeeRequest request);


}
