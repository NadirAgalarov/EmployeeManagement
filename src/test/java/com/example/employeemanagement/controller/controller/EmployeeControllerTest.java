package com.example.employeemanagement.controller.controller;


import com.example.employeemanagement.model.EmployeeRequest;
import com.example.employeemanagement.model.EmployeeResponse;
import com.example.employeemanagement.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    private static ObjectMapper objectMapper;

    @MockBean
    EmployeeServiceImpl employeeService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setUp(){
        objectMapper=new ObjectMapper();
    }

    @Test
    public void saveEmployeeTest() throws Exception {
        EmployeeRequest request= EmployeeRequest.builder().name("Aqil").surname("Abbasov").build();
        EmployeeResponse response=EmployeeResponse.builder().id(1).name("Aqil").surname("Abbasov").build();
        when(employeeService.saveEmployee(any(EmployeeRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/employee-management/employee")
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))
                        ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Aqil"))
                .andExpect(jsonPath("$.surname").value("Abbasov"));
    }

    @Test
    public void getEmployeeTest() throws Exception {
        int id=1;
        EmployeeResponse response=EmployeeResponse.builder().id(1).name("Aqil").surname("Abbasov").build();
        when(employeeService.getEmployee(anyInt())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/employee/{id}",id)
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Aqil"))
                .andExpect(jsonPath("$.surname").value("Abbasov"));
    }

    @Test
    public void getAllEmployeeTest() throws Exception {
        EmployeeResponse response=EmployeeResponse.builder().id(1).name("Aqil").surname("Abbasov").build();
        EmployeeResponse response2=EmployeeResponse.builder().id(2).name("Samir").surname("Quliyev").build();

        when(employeeService.getAllEmployee()).thenReturn(List.of(response,response2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employee-management/employee/all")
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"),
                                new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void updateEmployeeTest() throws Exception {
        int id=1;
        EmployeeRequest request= EmployeeRequest.builder().name("Aqil").surname("Abbasov").build();
        EmployeeResponse response=EmployeeResponse.builder().id(1).name("Aqil").surname("Abbasov").build();
        when(employeeService.updateEmployee(id,request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/employee-management/employee/{id}",id)
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"),
                                new SimpleGrantedAuthority("user")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Aqil"))
                .andExpect(jsonPath("$.surname").value("Abbasov"));
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        int id=1;
        when(employeeService.deleteEmployee(id)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/employee-management/employee/{id}",id)
                                .with(jwt().authorities(new SimpleGrantedAuthority("admin"),
                                        new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
