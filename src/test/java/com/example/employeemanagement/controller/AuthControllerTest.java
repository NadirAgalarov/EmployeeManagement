package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.request.LoginReguest;
import com.example.employeemanagement.model.RoleDto;
import com.example.employeemanagement.model.request.UserRequest;
import com.example.employeemanagement.model.response.LoginResponse;
import com.example.employeemanagement.model.response.UserResponse;
import com.example.employeemanagement.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @MockBean
    UserServiceImpl userService;
    @Autowired
    private MockMvc mockMvc;
    static ObjectMapper objectMapper;
    @BeforeAll
    private static void beforeAll(){
        objectMapper=new ObjectMapper();
    }

    @Test
    public void saveUserTest() throws Exception {
        UserRequest request=new UserRequest("Arzu","Veliyev",
                "arzu.valiyev@mail.com",
                "v.arzu","WCer349#ol", Set.of(new RoleDto(1)));
        UserResponse response=new UserResponse(1,"Arzu","Veliyev"
                ,"arzu.valiyev@mail.com");
        when(userService.saveUser(any(UserRequest.class))).thenReturn(Optional.of(response));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/employee-management/auth/register")
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))
                        ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Arzu"))
                .andExpect(jsonPath("$.surname").value("Veliyev"));
    }
    @Test
    public void loginTest() throws Exception {
        LoginReguest reguest=new LoginReguest("m.rauf","M.erufn23#");
        LoginResponse respons=new LoginResponse("jwtToken");
        when((userService.login(reguest))).thenReturn(Optional.of(respons));

        mockMvc.perform(MockMvcRequestBuilders.
                post("/api/v1/employee-management/auth/login")
                .with(jwt().authorities(new SimpleGrantedAuthority("user"),
                        new SimpleGrantedAuthority("admin")))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reguest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.token").value("jwtToken"));

    }
}
