package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.request.PositionRequest;
import com.example.employeemanagement.model.response.PositionResponse;
import com.example.employeemanagement.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${root.url}/position")
@RequiredArgsConstructor
public class PositionController {
    private  final PositionService positionService;

    @PostMapping
    public Optional<PositionResponse> savePosition(@RequestBody PositionRequest positionRequest){
        return positionService.savePosition(positionRequest);
    }

    @GetMapping("/{id}")
    public Optional<PositionResponse> getPositionById(@PathVariable int id){
        return positionService.getPositionById(id);
    }

    @GetMapping("/all")
    public List<PositionResponse> getAllPositions(){
        return positionService.getAllPosition();
    }

    @PatchMapping("/{id}")
    public Optional<PositionResponse> updatePosition(@RequestBody PositionRequest request, @PathVariable int id){
        return positionService.updatePosition(request,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePosition(@PathVariable int id){
        return positionService.deletePosition(id);
    }
}
