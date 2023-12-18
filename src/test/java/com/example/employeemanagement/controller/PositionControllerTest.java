package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.request.PositionRequest;
import com.example.employeemanagement.model.response.PositionResponse;
import com.example.employeemanagement.service.impl.PositionServiceImpl;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PositionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PositionServiceImpl positionService;

    private static ObjectMapper objectMapper;
    @BeforeAll
    static void before(){
        objectMapper=new ObjectMapper();
    }


    @Test
    public void savePositionTest() throws Exception {
        PositionRequest request=new PositionRequest("IT Specialist",1200.0,1);
        PositionResponse response=new PositionResponse(1,"IT Specialist",1200.0,1);
        when(positionService.savePosition(any(PositionRequest.class))).thenReturn(Optional.of(response));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employee-management/position")
                .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))
                        ).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("IT Specialist"))
                .andExpect(jsonPath("$.salary").value(1200.0));
    }
    @Test
    public void getPositionTest() throws Exception {
        int id=1;
        PositionResponse response=new PositionResponse(1,"IT Specialist",1200.0,4);
        when(positionService.getPositionById(anyInt())).thenReturn(Optional.of(response));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/position/{id}",id)
                .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("IT Specialist"))
                .andExpect(jsonPath("$.salary").value(1200.0));
    }

    @Test
    public void getAllPositionTest() throws Exception {
        PositionResponse response=new PositionResponse(1,"Python Developer",1200.0,2);
        PositionResponse response2=new PositionResponse(1,"Java Developer",1200.0,4);

        when(positionService.getAllPosition()).thenReturn(List.of(response,response2));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employee-management/position/all")
                .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void updatePositionTest() throws Exception {
        int id=1;
        PositionRequest request=new PositionRequest("Python Developer",1200.0,1);

        PositionResponse response=new PositionResponse(1,"Python Developer",1200.0,1);
        when(positionService.updatePosition(request,id)).thenReturn(Optional.of(response));

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/v1/employee-management/position/{id}",id)
                .with(jwt().authorities(new SimpleGrantedAuthority("admin"),new SimpleGrantedAuthority("user")))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Python Developer"))
                .andExpect(jsonPath("$.salary").value(1200.0));
    }

    @Test
    public void deletePositionTest() throws Exception {
        int id=1;
        when(positionService.deletePosition(id)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/employee-management/position/{id}",id)
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"),
                                new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
