package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.request.PositionRequest;
import com.example.employeemanagement.model.response.PositionResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-18T12:55:17+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 21 (Oracle Corporation)"
)
public class PositionMapperImpl extends PositionMapper {

    @Override
    public Position requestToEntity(PositionRequest request) {
        if ( request == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        if ( request != null ) {
            position.department( positionRequestToDepartment( request ) );
        }
        if ( request.name() != null ) {
            position.name( request.name() );
        }
        if ( request.salary() != null ) {
            position.salary( request.salary() );
        }

        return position.build();
    }

    @Override
    public PositionResponse entityToResponse(Position position) {
        if ( position == null ) {
            return null;
        }

        int departmentId = 0;
        int id = 0;
        String name = null;
        double salary = 0.0d;

        departmentId = positionDepartmentId( position );
        id = position.getId();
        if ( position.getName() != null ) {
            name = position.getName();
        }
        salary = position.getSalary();

        PositionResponse positionResponse = new PositionResponse( id, name, salary, departmentId );

        return positionResponse;
    }

    @Override
    public List<PositionResponse> entityListToResponseList(List<Position> positions) {
        if ( positions == null ) {
            return null;
        }

        List<PositionResponse> list = new ArrayList<PositionResponse>( positions.size() );
        for ( Position position : positions ) {
            list.add( entityToResponse( position ) );
        }

        return list;
    }

    @Override
    public void updateEntity(Position position, PositionRequest request) {
        if ( request == null ) {
            return;
        }

        if ( position.getDepartment() == null ) {
            position.setDepartment( Department.builder().build() );
        }
        positionRequestToDepartment1( request, position.getDepartment() );
        if ( request.name() != null ) {
            position.setName( request.name() );
        }
        if ( request.salary() != null ) {
            position.setSalary( request.salary() );
        }
    }

    protected Department positionRequestToDepartment(PositionRequest positionRequest) {
        if ( positionRequest == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        if ( positionRequest.departmentId() != null ) {
            department.id( positionRequest.departmentId() );
        }

        return department.build();
    }

    private int positionDepartmentId(Position position) {
        if ( position == null ) {
            return 0;
        }
        Department department = position.getDepartment();
        if ( department == null ) {
            return 0;
        }
        int id = department.getId();
        return id;
    }

    protected void positionRequestToDepartment1(PositionRequest positionRequest, Department mappingTarget) {
        if ( positionRequest == null ) {
            return;
        }

        if ( positionRequest.departmentId() != null ) {
            mappingTarget.setId( positionRequest.departmentId() );
        }
    }
}
