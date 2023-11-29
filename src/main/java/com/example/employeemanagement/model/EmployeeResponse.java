package com.example.employeemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private int id;
    private String name;
    private String surname;
    private DepartmentResponse departmentResponse;
    private PositionResponse positionResponse;
    private boolean status;
}
