package com.example.employeemanagement.controller;


import com.example.employeemanagement.model.request.EmployeeRequest;
import com.example.employeemanagement.model.response.EmployeeResponse;
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
import java.util.Optional;

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
        EmployeeRequest request= new EmployeeRequest("Aqil","Abbasov"
                ,"aqil.abbasov@mail.com",1,true);
        EmployeeResponse response=new EmployeeResponse(1,"Aqil","Abbasov",
                "aqil.abbasov@mail.com",1,true);
        when(employeeService.saveEmployee(any(EmployeeRequest.class))).thenReturn(Optional.of(response));

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
        EmployeeResponse response=new EmployeeResponse(1,"Aqil","Abbasov",
                "aqil.abbasov@mail.com", 1,true);
        when(employeeService.getEmployeeById(anyInt())).thenReturn(Optional.of(response));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/employee/{id}",id)
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Aqil"))
                .andExpect(jsonPath("$.surname").value("Abbasov"));
    }

    @Test
    public void getAllEmployeeTest() throws Exception {
        EmployeeResponse response=new EmployeeResponse(1,"Aqil","Abbasov",
                "aqil.abbasov@mail.com",1,true);
        EmployeeResponse response2=new EmployeeResponse(2,"Samir","Quliyev",
                "samir.quliyec@mail.com",2,true);

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
        EmployeeRequest request= new EmployeeRequest("Aqil","Abbasov"
                ,"aqil.abbasov@mail.com",1,true);
        EmployeeResponse response=new EmployeeResponse(1,"Aqil","Abbasov",
                "aqil.abbasov@mail.com", 1,true);
        when(employeeService.updateEmployee(id,request)).thenReturn(Optional.of(response));

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/v1/employee-management/employee/{id}",id)
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
