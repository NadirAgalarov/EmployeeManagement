package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.PositionRequest;
import com.example.employeemanagement.model.PositionResponse;
import com.example.employeemanagement.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee-management/position")
@RequiredArgsConstructor
public class PositionController {
    private  final PositionService positionService;

    @PostMapping
    public PositionResponse savePosition(@RequestBody PositionRequest positionRequest){
        return positionService.savePosition(positionRequest);
    }

    @GetMapping("/{id}")
    public PositionResponse getPosition(@PathVariable int id){
        return positionService.getPosition(id);
    }

    @GetMapping("/all")
    public List<PositionResponse> getAllPositions(){
        return positionService.getAllPosition();
    }

    @PutMapping("/{id}")
    public PositionResponse updatePosition(@RequestBody PositionRequest request, @PathVariable int id){
        return positionService.updatePosition(request,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePosition(@PathVariable int id){
        return positionService.deletePosition(id);
    }
}
