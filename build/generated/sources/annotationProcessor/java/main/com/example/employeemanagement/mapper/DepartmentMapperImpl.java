package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.model.request.DepartmentRequest;
import com.example.employeemanagement.model.response.DepartmentResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-18T12:55:17+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 21 (Oracle Corporation)"
)
public class DepartmentMapperImpl extends DepartmentMapper {

    @Override
    public Department requestToEntity(DepartmentRequest request) {
        if ( request == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        if ( request.name() != null ) {
            department.name( request.name() );
        }

        return department.build();
    }

    @Override
    public DepartmentResponse entityToResponse(Department department) {
        if ( department == null ) {
            return null;
        }

        int id = 0;
        String name = null;

        id = department.getId();
        if ( department.getName() != null ) {
            name = department.getName();
        }

        DepartmentResponse departmentResponse = new DepartmentResponse( id, name );

        return departmentResponse;
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

        if ( request.name() != null ) {
            department.setName( request.name() );
        }
    }
}
