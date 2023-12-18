package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.request.PositionRequest;
import com.example.employeemanagement.model.response.PositionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PositionService {
    Optional<PositionResponse> savePosition(PositionRequest request);
    Optional<PositionResponse> getPositionById (int id);
    List<PositionResponse> getAllPosition();

    Optional<PositionResponse> updatePosition(PositionRequest request, int id);

    ResponseEntity<?> deletePosition(int id);
    Position findById(int id);
}
