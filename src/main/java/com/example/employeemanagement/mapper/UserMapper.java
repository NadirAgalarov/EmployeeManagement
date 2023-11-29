package com.example.employeemanagement.mapper;


import com.example.employeemanagement.entity.User;
import com.example.employeemanagement.model.UserRequest;
import com.example.employeemanagement.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract  class UserMapper {
    public static final UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", source = "roleDtos")
    @Mapping(target = "status",expression = "java(getStatus())")
    public abstract User modelToEntity(UserRequest request);

    public abstract UserResponse entityTomodel(User user);
    @Named("getStatus")
    protected  boolean getStatus(){
        return true;
    }

}
