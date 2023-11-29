package com.example.employeemanagement.controller.service;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.NotFoundException;
import com.example.employeemanagement.model.DepartmentRequest;
import com.example.employeemanagement.model.DepartmentResponse;
import com.example.employeemanagement.model.PositionResponse;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.PositionRepository;
import com.example.employeemanagement.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
class DepartmentServiceTest {
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void deleteDepartmentTest(){
        int id=1;

        willDoNothing().given(departmentRepository).deleteById(id);
        departmentService.deleteDepartment(id);

        verify(departmentRepository, times(1)).deleteById(id);
    }

    @Test
    public void getDepartmentTest(){
        int id=1;
        Department department=Department.builder().id(id).name("IT").build();
        given(departmentRepository.findById(id)).willReturn(Optional.<Department>of(department));

        DepartmentResponse response = departmentService.getDepartment(id);

        assertThat(response).isNotNull();
        assertEquals(1,response.getId());
        assertEquals("IT",response.getName());

    }

    @Test
    public void getDepartmentExceptionTest(){
        given(departmentRepository.findById(anyInt())).willReturn(Optional.empty());

        assertThrows( NotFoundException.class, ()-> departmentService.getDepartment(anyInt()));
    }

    @Test
    public void getAllDepartmentTest(){

        Department department = Department.builder().id(1).name("IT").build();
        Department department1 = Department.builder().id(1).name("Network").build();

        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department,department1));
        List<DepartmentResponse> allDepartment = departmentService.getAllDepartment();

        assertNotNull(allDepartment);
        assertThat(allDepartment.size()).isEqualTo(2);
    }
    @Test
    public void saveDepartmentTest(){
        DepartmentRequest request=DepartmentRequest.builder().name("IT").positionIds(List.of(1)).build();
        Position position=Position.builder().id(1).name("Junior").salary(1200.0).build();
        Department department=Department.builder().id(1).name("IT").positions(List.of(position)).build();
        given(positionRepository.findAllById(anyCollection())).willReturn(List.of(position));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        DepartmentResponse response=departmentService.saveDepartment(request);

        assertNotNull(response);
    }
    @Test
    public void updateDepartmentTest(){
        int id=1;
        Position position=Position.builder().id(1).name("Junior").salary(1200.0).build();
        given(positionRepository.findAllById(anyCollection())).willReturn(List.of(position));
        DepartmentRequest request=DepartmentRequest.builder().name("Network").positionIds(List.of(1)).build();
        Department department=Department.builder().id(1).name("IT").positions(List.of(position)).build();

        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        DepartmentResponse response=departmentService.updateDepartment(id,request);

        assertNotNull(response);
    }
    @Test
    public void updateDepartmentException(){
        int id=1;
        DepartmentRequest request=DepartmentRequest.builder().name("Network").positionIds(List.of(1)).build();
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows( NotFoundException.class, ()-> departmentService.updateDepartment(id,request));
    }
}
