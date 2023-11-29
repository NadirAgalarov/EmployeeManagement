package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.User;
import com.example.employeemanagement.exception.NotFoundException;
import com.example.employeemanagement.mapper.UserMapper;
import com.example.employeemanagement.model.LoginReguest;
import com.example.employeemanagement.model.LoginResponse;
import com.example.employeemanagement.model.UserRequest;
import com.example.employeemanagement.model.UserResponse;
import com.example.employeemanagement.repository.UserRepository;
import com.example.employeemanagement.service.UserService;
import com.example.employeemanagement.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public UserResponse saveUser(UserRequest request) {
        LOGGER.info("ActionLog.saveUser.start request: {}",request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user= UserMapper.INSTANCE.modelToEntity(request);
        User savedUser=userRepository.save(user);
        UserResponse response=UserMapper.INSTANCE.entityTomodel(savedUser);

        LOGGER.info("ActionLog.saveUser.end request: {}",response);
        return response;
    }

    @Override
    public LoginResponse login(LoginReguest reguest) {
        LOGGER.info("ActionLog.login.start request: {}",reguest);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reguest.
                getUsername(),reguest.getPassword()));
        User user = userRepository.findByUsername(reguest.getUsername()).orElseThrow(
                () -> new NotFoundException("User not found for username" + reguest.getUsername()));
        String token = jwtService.generateToken(user);
        LOGGER.info("ActionLog.login.end request: {}",reguest);
        return LoginResponse.builder().token(token).build();
    }
}
