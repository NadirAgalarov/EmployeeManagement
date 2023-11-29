package com.example.employeemanagement.service;

import com.example.employeemanagement.model.PositionRequest;
import com.example.employeemanagement.model.PositionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PositionService {
    PositionResponse savePosition(PositionRequest request);
    PositionResponse getPosition (int id);
    List<PositionResponse> getAllPosition();

    PositionResponse updatePosition(PositionRequest request, int id);

    ResponseEntity<?> deletePosition(int id);
}
