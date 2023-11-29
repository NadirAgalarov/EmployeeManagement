package com.example.employeemanagement.controller.controller;

import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.model.PositionRequest;
import com.example.employeemanagement.model.PositionResponse;
import com.example.employeemanagement.service.impl.PositionServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.mockito.BDDMockito.willDoNothing;
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
        PositionRequest request=PositionRequest.builder().name("Junior").salary(1200.0).build();
        PositionResponse response=PositionResponse.builder().id(1).name("Junior").salary(1200.0).build();
        when(positionService.savePosition(any(PositionRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employee-management/position")
                .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))
                        ).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Junior"))
                .andExpect(jsonPath("$.salary").value(1200.0));
    }
    @Test
    public void getPositionTest() throws Exception {
        int id=1;
        PositionResponse response=PositionResponse.builder().id(1).name("Junior").salary(1200.0).build();
        when(positionService.getPosition(anyInt())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/position/{id}",id)
                .with(jwt().authorities(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Junior"))
                .andExpect(jsonPath("$.salary").value(1200.0));
    }

    @Test
    public void getAllPositionTest() throws Exception {
        PositionResponse response=PositionResponse.builder().id(1).name("Junior").salary(1200.0).build();
        PositionResponse response2=PositionResponse.builder().id(2).name("Senior").salary(2000.0).build();

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
        PositionRequest request=PositionRequest.builder().name("Junior").salary(1200.0).build();
        PositionResponse response=PositionResponse.builder().id(1).name("Junior").salary(1200.0).build();
        when(positionService.updatePosition(request,id)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/employee-management/position/{id}",id)
                .with(jwt().authorities(new SimpleGrantedAuthority("admin"),new SimpleGrantedAuthority("user")))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Junior"))
                .andExpect(jsonPath("$.salary").value(1200.0));
    }

    @Test
    public void deletePositionTest() throws Exception {
        int id=1;
//        willDoNothing().given(positionService).deletePosition(id);
        when(positionService.deletePosition(id)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/employee-management/position/{id}",id)
                        .with(jwt().authorities(new SimpleGrantedAuthority("admin"),
                                new SimpleGrantedAuthority("user"))))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
