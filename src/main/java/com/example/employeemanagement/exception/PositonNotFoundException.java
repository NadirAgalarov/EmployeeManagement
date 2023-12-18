package com.example.employeemanagement.exception;

import lombok.Getter;

@Getter
public class PositonNotFoundException extends RuntimeException{
    String message;
    public PositonNotFoundException(String message) {
        this.message=message;
    }
}
