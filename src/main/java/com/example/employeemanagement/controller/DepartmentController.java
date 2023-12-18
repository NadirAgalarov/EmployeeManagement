package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.request.DepartmentRequest;
import com.example.employeemanagement.model.response.DepartmentResponse;
import com.example.employeemanagement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${root.url}/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public Optional<DepartmentResponse> saveDepartment(@RequestBody DepartmentRequest request){
        return departmentService.saveDepartment(request);
    }

    @GetMapping("/{id}")
    public Optional<DepartmentResponse> getDepartamentById(@PathVariable int id){
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/all")
    public List<DepartmentResponse> getAllDepartments(){
        return departmentService.getAllDepartment();
    }

    @PatchMapping("/{id}")
    public Optional<DepartmentResponse> updateDepartment(@PathVariable int id,@RequestBody DepartmentRequest request){
        return departmentService.updateDepartment(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id){
        return departmentService.deleteDepartment(id);
    }
}
