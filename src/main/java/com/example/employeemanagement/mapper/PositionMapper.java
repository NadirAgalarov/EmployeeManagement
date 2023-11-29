package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.PositionRequest;
import com.example.employeemanagement.model.PositionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PositionMapper {
    public static final PositionMapper INSTANCE= Mappers.getMapper(PositionMapper.class);

    public abstract Position requestToEntity(PositionRequest request);

    public abstract PositionResponse entityToResponse(Position position);

    public abstract List<PositionResponse> entityListToResponseList(List<Position> positions);
    public abstract void updateEntity(@MappingTarget Position position, PositionRequest request);

}
