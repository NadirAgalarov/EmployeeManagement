package com.example.employeemanagement.handler;

import com.example.employeemanagement.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.employeemanagement.model.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> datanotFound(NotFoundException exception){
        int status= HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(status)
                .body(ErrorResponse.builder().status(status)
                        .message(exception.getMessage()).build());
    }
}
