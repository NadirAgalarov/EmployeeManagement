package com.example.employeemanagement.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends  RuntimeException{
    private String message;

    public NotFoundException(String message){this.message=message;}
}
