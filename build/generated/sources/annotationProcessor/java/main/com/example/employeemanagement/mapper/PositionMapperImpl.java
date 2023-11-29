package com.example.employeemanagement.mapper;

import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.PositionRequest;
import com.example.employeemanagement.model.PositionResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-28T18:07:00+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 21 (Oracle Corporation)"
)
public class PositionMapperImpl extends PositionMapper {

    @Override
    public Position requestToEntity(PositionRequest request) {
        if ( request == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        if ( request.getName() != null ) {
            position.name( request.getName() );
        }
        position.salary( request.getSalary() );

        return position.build();
    }

    @Override
    public PositionResponse entityToResponse(Position position) {
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

        if ( request.getName() != null ) {
            position.setName( request.getName() );
        }
        position.setSalary( request.getSalary() );
    }
}
