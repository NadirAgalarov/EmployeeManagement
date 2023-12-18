package com.example.employeemanagement.handler;

import com.example.employeemanagement.exception.DepartmentNotFoundException;
import com.example.employeemanagement.exception.PositonNotFoundException;
import com.example.employeemanagement.exception.UserNotFoundException;
import com.example.employeemanagement.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.employeemanagement.model.response.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    int status;
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> departmentNotFound(DepartmentNotFoundException exception){
        status= HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(status)
                .body(new ErrorResponse(status,exception.getMessage()));
    }

    @ExceptionHandler(PositonNotFoundException.class)
    public ResponseEntity<ErrorResponse> positonNotFound(PositonNotFoundException exception){
        status= HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(status)
                .body(new ErrorResponse(status,exception.getMessage()));
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> employeeNotFound(EmployeeNotFoundException exception){
        status= HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(status)
                .body(new ErrorResponse(status,exception.getMessage()));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException exception){
        status= HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(status)
                .body(new ErrorResponse(status,exception.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationEmail(MethodArgumentNotValidException exception){
        status= HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(status)
                .body(new ErrorResponse(status,exception.getBindingResult()
                                .getAllErrors().getFirst().getDefaultMessage()));
    }
}
