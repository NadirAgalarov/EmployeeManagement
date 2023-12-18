package com.example.employeemanagement.service;

import com.example.employeemanagement.model.request.EmployeeRequest;
import com.example.employeemanagement.model.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<EmployeeResponse> saveEmployee(EmployeeRequest request);
    Optional<EmployeeResponse> getEmployeeById(int id);
    List<EmployeeResponse> getAllEmployee();
    Optional<EmployeeResponse> updateEmployee(int id,EmployeeRequest request);
    ResponseEntity<?> deleteEmployee(int id);
}
