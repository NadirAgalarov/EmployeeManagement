package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.request.EmployeeRequest;
import com.example.employeemanagement.model.response.EmployeeResponse;
import com.example.employeemanagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${root.url}/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public Optional<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequest request){
        return employeeService.saveEmployee(request);
    }
    @GetMapping("/{id}")
    public Optional<EmployeeResponse> getEmployeeById(@PathVariable int id){

        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/all")
    public List<EmployeeResponse> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PatchMapping("/{id}")
    public Optional<EmployeeResponse> updateEmployee(@PathVariable int id,@RequestBody EmployeeRequest request){
        return employeeService.updateEmployee(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }
}
