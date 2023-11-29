package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.EmployeeRequest;
import com.example.employeemanagement.model.EmployeeResponse;
import com.example.employeemanagement.service.EmployeeService;
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
@RequestMapping("/api/v1/employee-management/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponse saveEmployee(@RequestBody EmployeeRequest request){
        return employeeService.saveEmployee(request);
    }
    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }
    @GetMapping("/all")
    public List<EmployeeResponse> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable int id,@RequestBody EmployeeRequest request){
        return employeeService.updateEmployee(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }
}
