package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.model.request.DepartmentRequest;
import com.example.employeemanagement.model.response.DepartmentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Optional<DepartmentResponse> saveDepartment(DepartmentRequest request);
    Optional<DepartmentResponse> getDepartmentById(int id);
    List<DepartmentResponse> getAllDepartment();
    Optional<DepartmentResponse> updateDepartment(int id, DepartmentRequest request);
    ResponseEntity<?> deleteDepartment(int id);
    Department findById(int id);
}
