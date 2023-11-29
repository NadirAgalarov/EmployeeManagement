package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.NotFoundException;
import com.example.employeemanagement.mapper.PositionMapper;
import com.example.employeemanagement.model.PositionRequest;
import com.example.employeemanagement.model.PositionResponse;
import com.example.employeemanagement.repository.PositionRepository;
import com.example.employeemanagement.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private  final PositionRepository positionRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(PositionServiceImpl.class);
    @Override
    public PositionResponse savePosition(PositionRequest request) {
        LOGGER.info("ActionLog.savePosition.start request: {}",request);
        Position position= PositionMapper.INSTANCE.requestToEntity(request);
        Position savedPosition= positionRepository.save(position);
        PositionResponse response=PositionMapper.INSTANCE.entityToResponse(savedPosition);
        LOGGER.info("ActionLog.savePosition.end response: {}", response);
        return response;
    }

    @Override
    public PositionResponse getPosition(int id) {
        LOGGER.info("ActionLog.getPosition.start id: {}",id);
        Position position=positionRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Position not found for id"+ id) );
        PositionResponse response=PositionMapper.INSTANCE.entityToResponse(position);
        LOGGER.info("ActionLog.getPosition.end response: {}",response);
        return response;
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
    public PositionResponse updatePosition(PositionRequest request, int id) {
        LOGGER.info("ActionLog.updatePosition.start id: {}",id);
        Position position=positionRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Position not found for id"+ id) );
        PositionMapper.INSTANCE.updateEntity(position,request);
        Position savedPosition=positionRepository.save(position);
        PositionResponse response=PositionMapper.INSTANCE.entityToResponse(savedPosition);
        LOGGER.info("ActionLog.updatePosition.end response: {}",response);
        return response;
    }

    @Override
    public ResponseEntity<?> deletePosition(int id) {
        LOGGER.info("ActionLog.deletePosition.start id: {}",id);
        positionRepository.deleteById(id);
        LOGGER.info("ActionLog.deletePosition.end for id: {}", id);
        return ResponseEntity.ok().build();
    }
}
