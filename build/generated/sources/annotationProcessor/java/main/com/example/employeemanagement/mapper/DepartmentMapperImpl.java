package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.DepartmentRequest;
import com.example.employeemanagement.model.DepartmentResponse;
import com.example.employeemanagement.model.PositionResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-28T21:17:09+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 21 (Oracle Corporation)"
)
public class DepartmentMapperImpl extends DepartmentMapper {

    @Override
    public Position integerToPosition(Integer ids) {
        if ( ids == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        if ( ids != null ) {
            position.id( ids );
        }

        return position.build();
    }

    @Override
    public Department requestToEntity(DepartmentRequest request) {
        if ( request == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        List<Position> list = integerListToPositionList( request.getPositionIds() );
        if ( list != null ) {
            department.positions( list );
        }
        if ( request.getName() != null ) {
            department.name( request.getName() );
        }

        return department.build();
    }

    @Override
    public DepartmentResponse entityToResponse(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentResponse.DepartmentResponseBuilder departmentResponse = DepartmentResponse.builder();

        departmentResponse.id( department.getId() );
        if ( department.getName() != null ) {
            departmentResponse.name( department.getName() );
        }
        List<PositionResponse> list = positionListToPositionResponseList( department.getPositions() );
        if ( list != null ) {
            departmentResponse.positions( list );
        }

        return departmentResponse.build();
    }

    @Override
    public List<DepartmentResponse> entityListToResponseList(List<Department> departments) {
        if ( departments == null ) {
            return null;
        }

        List<DepartmentResponse> list = new ArrayList<DepartmentResponse>( departments.size() );
        for ( Department department : departments ) {
            list.add( entityToResponse( department ) );
        }

        return list;
    }

    @Override
    public void updateEntity(Department department, DepartmentRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            department.setName( request.getName() );
        }
    }

    protected List<Position> integerListToPositionList(List<Integer> list) {
        if ( list == null ) {
            return null;
        }

        List<Position> list1 = new ArrayList<Position>( list.size() );
        for ( Integer integer : list ) {
            list1.add( integerToPosition( integer ) );
        }

        return list1;
    }

    protected PositionResponse positionToPositionResponse(Position position) {
        if ( position == null ) {
            return null;
        }

        PositionResponse.PositionResponseBuilder positionResponse = PositionResponse.builder();

        positionResponse.id( position.getId() );
        if ( position.getName() != null ) {
            positionResponse.name( position.getName() );
        }
        positionResponse.salary( position.getSalary() );

        return positionResponse.build();
    }

    protected List<PositionResponse> positionListToPositionResponseList(List<Position> list) {
        if ( list == null ) {
            return null;
        }

        List<PositionResponse> list1 = new ArrayList<PositionResponse>( list.size() );
        for ( Position position : list ) {
            list1.add( positionToPositionResponse( position ) );
        }

        return list1;
    }
}
