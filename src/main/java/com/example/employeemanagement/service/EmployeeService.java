package com.example.employeemanagement.service;

import com.example.employeemanagement.model.EmployeeRequest;
import com.example.employeemanagement.model.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse saveEmployee(EmployeeRequest request);
    EmployeeResponse getEmployee(int id);
    List<EmployeeResponse> getAllEmployee();
    EmployeeResponse updateEmployee(int id,EmployeeRequest request);
    ResponseEntity<?> deleteEmployee(int id);
}
