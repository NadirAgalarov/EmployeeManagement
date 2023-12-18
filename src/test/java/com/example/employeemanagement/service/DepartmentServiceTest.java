package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.exception.DepartmentNotFoundException;
import com.example.employeemanagement.model.request.DepartmentRequest;
import com.example.employeemanagement.model.response.DepartmentResponse;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
class DepartmentServiceTest {
    @Mock
    private DepartmentRepository departmentRepository;
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
    public void getDepartmentByIdTest(){
        int id=1;
        Department department=Department.builder().id(id).name("IT").build();
        given(departmentRepository.findById(id)).willReturn(Optional.<Department>of(department));

        Optional<DepartmentResponse> response = departmentService.getDepartmentById(id);

        assertThat(response).isNotNull();
        assertEquals(1,response.get().id());
        assertEquals("IT",response.get().name());

    }

    @Test
    public void getDepartmentExceptionTest(){
        given(departmentRepository.findById(anyInt())).willReturn(Optional.empty());

        assertThrows( DepartmentNotFoundException.class, ()-> departmentService.getDepartmentById(anyInt()));
    }

    @Test
    public void getAllDepartmentTest(){

        Department department = Department.builder().id(1).name("IT").build();
        Department department1 = Department.builder().id(1).name("Security").build();

        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department,department1));
        List<DepartmentResponse> allDepartment = departmentService.getAllDepartment();

        assertNotNull(allDepartment);
        assertThat(allDepartment.size()).isEqualTo(2);
    }
    @Test
    public void saveDepartmentTest(){
        DepartmentRequest request= new DepartmentRequest("IT");
        Department department=Department.builder().id(1).name("IT").build();
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Optional<DepartmentResponse> response=departmentService.saveDepartment(request);

        assertNotNull(response);
        assertEquals("IT",response.get().name());
    }
    @Test
    public void updateDepartmentTest(){
        int id=1;
        DepartmentRequest request= new DepartmentRequest("Security");
        Department department=Department.builder().id(1).name("IT").build();

        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Optional<DepartmentResponse> response=departmentService.updateDepartment(id,request);

        assertNotNull(response);
        assertEquals("Security",response.get().name());
    }
    @Test
    public void updateDepartmentException(){
        int id=1;
        DepartmentRequest request= new DepartmentRequest("IT");
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows( DepartmentNotFoundException.class, ()-> departmentService.updateDepartment(id,request));
    }
}
