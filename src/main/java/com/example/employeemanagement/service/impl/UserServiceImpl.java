package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.User;
import com.example.employeemanagement.exception.UserNotFoundException;
import com.example.employeemanagement.mapper.UserMapper;
import com.example.employeemanagement.model.request.LoginReguest;
import com.example.employeemanagement.model.response.LoginResponse;
import com.example.employeemanagement.model.request.UserRequest;
import com.example.employeemanagement.model.response.UserResponse;
import com.example.employeemanagement.repository.UserRepository;
import com.example.employeemanagement.service.UserService;
import com.example.employeemanagement.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public Optional<UserResponse> saveUser(UserRequest request) {
        LOGGER.info("ActionLog.saveUser.start request: {}",request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user= UserMapper.INSTANCE.modelToEntity(request);
        User savedUser=userRepository.save(user);
        UserResponse response=UserMapper.INSTANCE.entityTomodel(savedUser);

        LOGGER.info("ActionLog.saveUser.end request: {}",response);
        return Optional.of(response);
    }

    @Override
    public Optional<LoginResponse> login(LoginReguest reguest) {
        LOGGER.info("ActionLog.login.start request: {}",reguest);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reguest.
                username(),reguest.password()));
        User user = userRepository.findByUsername(reguest.username()).orElseThrow(
                () -> new UserNotFoundException("User not found for username" + reguest.username()));
        String token = jwtProvider.generateToken(user);
        LOGGER.info("ActionLog.login.end request: {}",reguest);
        return Optional.of(new LoginResponse(token));
    }
}
