package com.example.employeemanagement.service;

import com.example.employeemanagement.model.request.LoginReguest;
import com.example.employeemanagement.model.response.LoginResponse;
import com.example.employeemanagement.model.request.UserRequest;
import com.example.employeemanagement.model.response.UserResponse;

import java.util.Optional;

public interface UserService {
    Optional<UserResponse> saveUser(UserRequest request);
    Optional<LoginResponse> login(LoginReguest reguest);
}
