package com.example.employeemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String name;
    private String surname;
    private int departmentId;
    private int positionId;
    private boolean status;
}
