package com.example.employeemanagement.service;

import com.example.employeemanagement.model.LoginReguest;
import com.example.employeemanagement.model.LoginResponse;
import com.example.employeemanagement.model.UserRequest;
import com.example.employeemanagement.model.UserResponse;

public interface UserService {
    UserResponse saveUser(UserRequest request);
    LoginResponse login(LoginReguest reguest);
}
