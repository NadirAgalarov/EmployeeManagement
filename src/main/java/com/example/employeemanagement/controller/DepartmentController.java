package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.DepartmentRequest;
import com.example.employeemanagement.model.DepartmentResponse;
import com.example.employeemanagement.service.DepartmentService;
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
@RequestMapping("/api/v1/employee-management/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public DepartmentResponse saveDepartment(@RequestBody DepartmentRequest request){
        return departmentService.saveDepartment(request);
    }

    @GetMapping("/{id}")
    public DepartmentResponse getDepartament(@PathVariable int id){
        return departmentService.getDepartment(id);
    }

    @GetMapping("/all")
    public List<DepartmentResponse> getAllDepartments(){
        return departmentService.getAllDepartment();
    }

    @PutMapping("/{id}")
    public DepartmentResponse updateDepartment(@PathVariable int id,@RequestBody DepartmentRequest request){
        return departmentService.updateDepartment(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id){
        return departmentService.deleteDepartment(id);
    }
}
