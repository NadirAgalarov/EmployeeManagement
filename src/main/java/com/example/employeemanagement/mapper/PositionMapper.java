package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.request.PositionRequest;
import com.example.employeemanagement.model.response.PositionResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PositionMapper {
    public static final PositionMapper INSTANCE= Mappers.getMapper(PositionMapper.class);
    @Mapping(target = "department.id",source = "departmentId")
    public abstract Position requestToEntity(PositionRequest request);
    @Mapping(target = "departmentId",source = "department.id")
    public abstract PositionResponse entityToResponse(Position position);

    public abstract List<PositionResponse> entityListToResponseList(List<Position> positions);
    @Mapping(target = "department.id",source = "departmentId")
    public abstract void updateEntity(@MappingTarget Position position, PositionRequest request);

}
