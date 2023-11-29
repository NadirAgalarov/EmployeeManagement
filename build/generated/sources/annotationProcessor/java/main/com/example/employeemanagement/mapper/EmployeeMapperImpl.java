package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.DepartmentResponse;
import com.example.employeemanagement.model.EmployeeRequest;
import com.example.employeemanagement.model.EmployeeResponse;
import com.example.employeemanagement.model.PositionResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-28T21:17:09+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 21 (Oracle Corporation)"
)
public class EmployeeMapperImpl extends EmployeeMapper {

    @Override
    public EmployeeResponse entityToResponse(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponse.EmployeeResponseBuilder employeeResponse = EmployeeResponse.builder();

        if ( employee.getDepartment() != null ) {
            employeeResponse.departmentResponse( departmentToDepartmentResponse( employee.getDepartment() ) );
        }
        if ( employee.getPosition() != null ) {
            employeeResponse.positionResponse( positionToPositionResponse( employee.getPosition() ) );
        }
        employeeResponse.id( employee.getId() );
        if ( employee.getName() != null ) {
            employeeResponse.name( employee.getName() );
        }
        if ( employee.getSurname() != null ) {
            employeeResponse.surname( employee.getSurname() );
        }
        employeeResponse.status( employee.isStatus() );

        return employeeResponse.build();
    }

    @Override
    public Employee requestToEntity(EmployeeRequest request) {
        if ( request == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        if ( request != null ) {
            employee.position( employeeRequestToPosition( request ) );
        }
        if ( request != null ) {
            employee.department( employeeRequestToDepartment( request ) );
        }
        if ( request.getName() != null ) {
            employee.name( request.getName() );
        }
        if ( request.getSurname() != null ) {
            employee.surname( request.getSurname() );
        }
        employee.status( request.isStatus() );

        return employee.build();
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
        if ( employee.getDepartment() == null ) {
            employee.setDepartment( Department.builder().build() );
        }
        employeeRequestToDepartment1( request, employee.getDepartment() );
        if ( request.getName() != null ) {
            employee.setName( request.getName() );
        }
        if ( request.getSurname() != null ) {
            employee.setSurname( request.getSurname() );
        }
        employee.setStatus( request.isStatus() );
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

    protected DepartmentResponse departmentToDepartmentResponse(Department department) {
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

    protected Position employeeRequestToPosition(EmployeeRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        position.id( employeeRequest.getPositionId() );

        return position.build();
    }

    protected Department employeeRequestToDepartment(EmployeeRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        department.id( employeeRequest.getDepartmentId() );

        return department.build();
    }

    protected void employeeRequestToPosition1(EmployeeRequest employeeRequest, Position mappingTarget) {
        if ( employeeRequest == null ) {
            return;
        }

        mappingTarget.setId( employeeRequest.getPositionId() );
    }

    protected void employeeRequestToDepartment1(EmployeeRequest employeeRequest, Department mappingTarget) {
        if ( employeeRequest == null ) {
            return;
        }

        mappingTarget.setId( employeeRequest.getDepartmentId() );
    }
}
