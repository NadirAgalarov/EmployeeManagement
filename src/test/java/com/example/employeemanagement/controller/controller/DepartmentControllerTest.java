package com.example.employeemanagement.controller.controller;

import com.example.employeemanagement.model.DepartmentRequest;
import com.example.employeemanagement.model.DepartmentResponse;
import com.example.employeemanagement.model.PositionRequest;
import com.example.employeemanagement.model.PositionResponse;
import com.example.employeemanagement.service.impl.DepartmentServiceImpl;
import com.example.employeemanagement.service.impl.PositionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.codec.Decoder;
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
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentServiceImpl departmentService;

    private static ObjectMapper objectMapper;
    @BeforeAll
    static void before(){
        objectMapper=new ObjectMapper();
    }

    @Test
    public void saveDepartmentTest() throws Exception {
        DepartmentRequest request= DepartmentRequest.builder().name("IT").build();
        DepartmentResponse response=DepartmentResponse.builder().id(1).name("IT").build();
        when(departmentService.saveDepartment(any(DepartmentRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/employee-management/department")
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))
                        ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("IT"));
    }

    @Test
    public void getDepartmentTest() throws Exception {
        int id=1;
        DepartmentResponse response=DepartmentResponse.builder().id(id).name("IT").build();
        when(departmentService.getDepartment(anyInt())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/department/{id}",id)
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("IT"));
    }

    @Test
    public void getAllDepartmentTest() throws Exception {
        DepartmentResponse response=DepartmentResponse.builder().id(1).name("IT").build();
        DepartmentResponse response2=DepartmentResponse.builder().id(2).name("IT").build();

        when(departmentService.getAllDepartment()).thenReturn(List.of(response,response2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employee-management/department/all")
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"),
                                new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void updateDepartmentTest() throws Exception {
        int id=1;
        DepartmentRequest request= DepartmentRequest.builder().name("IT").build();
        DepartmentResponse response=DepartmentResponse.builder().id(1).name("IT").build();
        when(departmentService.updateDepartment(id,request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/employee-management/department/{id}",id)
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"),
                                new SimpleGrantedAuthority("user")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("IT"));
    }

    @Test
    public void deleteDepartmentTest() throws Exception {
        int id=1;
        when(departmentService.deleteDepartment(id)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/employee-management/department/{id}",id)
                                .with(jwt().authorities(new SimpleGrantedAuthority("admin"),
                                        new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
