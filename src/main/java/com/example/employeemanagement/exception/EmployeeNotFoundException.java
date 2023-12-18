package com.example.employeemanagement.exception;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends RuntimeException{
    private String message;

    public EmployeeNotFoundException(String message){this.message=message;}
}
