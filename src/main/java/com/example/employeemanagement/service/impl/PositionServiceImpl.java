package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.PositonNotFoundException;
import com.example.employeemanagement.mapper.PositionMapper;
import com.example.employeemanagement.model.request.PositionRequest;
import com.example.employeemanagement.model.response.PositionResponse;
import com.example.employeemanagement.repository.PositionRepository;
import com.example.employeemanagement.service.DepartmentService;
import com.example.employeemanagement.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private  final PositionRepository positionRepository;
    private final DepartmentService departmentService;
    private static final Logger LOGGER= LoggerFactory.getLogger(PositionServiceImpl.class);
    @Override
    public Optional<PositionResponse> savePosition(PositionRequest request) {
        LOGGER.info("ActionLog.savePosition.start request: {}",request);
        Position position= PositionMapper.INSTANCE.requestToEntity(request);
        Department department=departmentService.findById(position.getDepartment().getId());
        position.setDepartment(department);
        Position savedPosition= positionRepository.save(position);
        PositionResponse response=PositionMapper.INSTANCE.entityToResponse(savedPosition);
        LOGGER.info("ActionLog.savePosition.end response: {}", response);
        return Optional.of(response);
    }

    @Override
    public Optional<PositionResponse> getPositionById(int id) {
        LOGGER.info("ActionLog.getPosition.start id: {}",id);
        Position position=positionRepository.findById(id).orElseThrow(
                ()-> new PositonNotFoundException("Position not found for id"+ id) );
        PositionResponse response=PositionMapper.INSTANCE.entityToResponse(position);
        LOGGER.info("ActionLog.getPosition.end response: {}",response);
        return Optional.of(response);
    }
    @Override
    public List<PositionResponse> getAllPosition() {
        LOGGER.info("ActionLog.getAllPosition.start ");
        List<Position> positions = positionRepository.findAll();
        List<PositionResponse> responses=PositionMapper.INSTANCE.entityListToResponseList(positions);
        LOGGER.info("ActionLog.getAllPosition.end position count: {}",responses.size());
        return responses;
    }

    @Override
    public Optional<PositionResponse> updatePosition(PositionRequest request, int id) {
        LOGGER.info("ActionLog.updatePosition.start id: {}",id);
        Position position=positionRepository.findById(id).orElseThrow(
                ()-> new PositonNotFoundException("Position not found for id"+ id) );
        if(request.departmentId()!=null){
        Department department=departmentService.findById(request.departmentId());
        position.setDepartment(department);}
        PositionMapper.INSTANCE.updateEntity(position,request);
        Position savedPosition=positionRepository.save(position);
        PositionResponse response=PositionMapper.INSTANCE.entityToResponse(savedPosition);
        LOGGER.info("ActionLog.updatePosition.end response: {}",response);
        return Optional.of(response);
    }

    @Override
    public ResponseEntity<?> deletePosition(int id) {
        LOGGER.info("ActionLog.deletePosition.start id: {}",id);
        positionRepository.deleteById(id);
        LOGGER.info("ActionLog.deletePosition.end for id: {}", id);
        return ResponseEntity.ok().build();
    }

    @Override
    public Position findById(int id) {
        LOGGER.info("ActionLog.findById.start id: {}",id);
        Position position=positionRepository.findById(id).orElseThrow(
                ()-> new PositonNotFoundException("Position not found for id"+ id) );
        LOGGER.info("ActionLog.findById.end response: {}",position);
        return position;
    }
}
