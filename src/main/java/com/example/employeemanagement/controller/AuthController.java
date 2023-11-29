package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.LoginReguest;
import com.example.employeemanagement.model.LoginResponse;
import com.example.employeemanagement.model.UserRequest;
import com.example.employeemanagement.model.UserResponse;
import com.example.employeemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee-management/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponse saveUser(@RequestBody UserRequest request) {
        return userService.saveUser(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginReguest request) {
        return userService.login(request);
    }

}
