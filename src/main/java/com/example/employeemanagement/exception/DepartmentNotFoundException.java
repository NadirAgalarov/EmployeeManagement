package com.example.employeemanagement.exception;

import lombok.Getter;

@Getter
public class DepartmentNotFoundException extends RuntimeException{
    private String message;

    public DepartmentNotFoundException(String message){this.message=message;}
}
