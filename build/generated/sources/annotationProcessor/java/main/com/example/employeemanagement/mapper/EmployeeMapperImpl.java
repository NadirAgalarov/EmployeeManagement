package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.request.EmployeeRequest;
import com.example.employeemanagement.model.response.EmployeeResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-18T12:55:17+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 21 (Oracle Corporation)"
)
public class EmployeeMapperImpl extends EmployeeMapper {

    @Override
    public Employee requestToEntity(EmployeeRequest request) {
        if ( request == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        if ( request != null ) {
            employee.position( employeeRequestToPosition( request ) );
        }
        if ( request.name() != null ) {
            employee.name( request.name() );
        }
        if ( request.surname() != null ) {
            employee.surname( request.surname() );
        }
        if ( request.email() != null ) {
            employee.email( request.email() );
        }
        if ( request.status() != null ) {
            employee.status( request.status() );
        }

        return employee.build();
    }

    @Override
    public EmployeeResponse entityToResponse(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        int positionId = 0;
        int id = 0;
        String name = null;
        String surname = null;
        String email = null;
        boolean status = false;

        positionId = employeePositionId( employee );
        id = employee.getId();
        if ( employee.getName() != null ) {
            name = employee.getName();
        }
        if ( employee.getSurname() != null ) {
            surname = employee.getSurname();
        }
        if ( employee.getEmail() != null ) {
            email = employee.getEmail();
        }
        status = employee.isStatus();

        EmployeeResponse employeeResponse = new EmployeeResponse( id, name, surname, email, positionId, status );

        return employeeResponse;
    }

    @Override
    public List<EmployeeResponse> entityListToResponseList(List<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeResponse> list = new ArrayList<EmployeeResponse>( employees.size() );
        for ( Employee employee : employees ) {
            list.add( entityToResponse( employee ) );
        }

        return list;
    }

    @Override
    public void updateEntity(Employee employee, EmployeeRequest request) {
        if ( request == null ) {
            return;
        }

        if ( employee.getPosition() == null ) {
            employee.setPosition( Position.builder().build() );
        }
        employeeRequestToPosition1( request, employee.getPosition() );
        if ( request.name() != null ) {
            employee.setName( request.name() );
        }
        if ( request.surname() != null ) {
            employee.setSurname( request.surname() );
        }
        if ( request.email() != null ) {
            employee.setEmail( request.email() );
        }
        if ( request.status() != null ) {
            employee.setStatus( request.status() );
        }
    }

    protected Position employeeRequestToPosition(EmployeeRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        if ( employeeRequest.positionId() != null ) {
            position.id( employeeRequest.positionId() );
        }

        return position.build();
    }

    private int employeePositionId(Employee employee) {
        if ( employee == null ) {
            return 0;
        }
        Position position = employee.getPosition();
        if ( position == null ) {
            return 0;
        }
        int id = position.getId();
        return id;
    }

    protected void employeeRequestToPosition1(EmployeeRequest employeeRequest, Position mappingTarget) {
        if ( employeeRequest == null ) {
            return;
        }

        if ( employeeRequest.positionId() != null ) {
            mappingTarget.setId( employeeRequest.positionId() );
        }
    }
}
