package com.example.employeemanagement.controller.controller;

import com.example.employeemanagement.model.LoginReguest;
import com.example.employeemanagement.model.LoginResponse;
import com.example.employeemanagement.model.UserRequest;
import com.example.employeemanagement.model.UserResponse;
import com.example.employeemanagement.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        UserRequest request=UserRequest.builder().name("Arzu").surname("Veliyev").build();
        UserResponse response=UserResponse.builder().id(1).name("Arzu").surname("Veliyev").build();
        when(userService.saveUser(any(UserRequest.class))).thenReturn(response);

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
        LoginReguest reguest=LoginReguest.builder().username("m.rauf").password("m.12345").build();
        LoginResponse respons=LoginResponse.builder().token("jwtToken").build();
        when((userService.login(reguest))).thenReturn(respons);

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
