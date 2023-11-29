package com.example.employeemanagement.service;

import com.example.employeemanagement.model.DepartmentRequest;
import com.example.employeemanagement.model.DepartmentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse saveDepartment(DepartmentRequest request);
    DepartmentResponse getDepartment(int id);
    List<DepartmentResponse> getAllDepartment();
    DepartmentResponse updateDepartment(int id, DepartmentRequest request);
    ResponseEntity<?> deleteDepartment(int id);
}
