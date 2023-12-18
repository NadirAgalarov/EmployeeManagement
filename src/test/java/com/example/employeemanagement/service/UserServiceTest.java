package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.User;
import com.example.employeemanagement.exception.UserNotFoundException;
import com.example.employeemanagement.model.request.LoginReguest;
import com.example.employeemanagement.model.request.UserRequest;
import com.example.employeemanagement.model.response.UserResponse;
import com.example.employeemanagement.repository.UserRepository;
import com.example.employeemanagement.service.impl.UserServiceImpl;
import com.example.employeemanagement.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtProvider jwtProvider;



    @Test
    public void saveUser(){
        UserRequest request=UserRequest.builder().name("cavid").surname("Muradov")
                .username("m.cavid").password("12345").build();
        User user=User.builder().id(1).name("cavid").surname("Muradov")
                .username("m.cavid").password("12345").build();
        when(passwordEncoder.encode(any(String.class))).thenReturn(request.getPassword());
        when(userRepository.save(any(User.class))).thenReturn(user);

        Optional<UserResponse> response=userService.saveUser(request);
        assertNotNull(response);
    }
    @Test
    public void loginTest(){
        LoginReguest reguest=new LoginReguest("m.cavid","12345");
        User user=User.builder().id(1).name("cavid").surname("Muradov")
                .username("m.cavid").password("12345").build();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reguest.
                username(),reguest.password()));
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(user));
        when(jwtProvider.generateToken(any(User.class))).thenReturn("newToken");
        String token= String.valueOf(userService.login(reguest));

        assertNotNull(token);
    }
    @Test
    public void loginExceptionTest(){
        LoginReguest reguest=new LoginReguest("m.cavid","12345");
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.empty());
        assertThrows( UserNotFoundException.class, ()-> userService.login(reguest));
    }
}
