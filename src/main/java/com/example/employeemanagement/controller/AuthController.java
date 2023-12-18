package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.request.LoginReguest;
import com.example.employeemanagement.model.response.LoginResponse;
import com.example.employeemanagement.model.request.UserRequest;
import com.example.employeemanagement.model.response.UserResponse;
import com.example.employeemanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("${root.url}/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public Optional<UserResponse> saveUser(@Valid @RequestBody UserRequest request) {
        return userService.saveUser(request);
    }

    @PostMapping("/login")
    public Optional<LoginResponse> login(@RequestBody LoginReguest request) {
        return userService.login(request);
    }

}
