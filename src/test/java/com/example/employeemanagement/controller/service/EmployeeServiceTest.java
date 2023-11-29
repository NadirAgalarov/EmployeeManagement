package com.example.employeemanagement.controller.service;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.NotFoundException;
import com.example.employeemanagement.model.EmployeeRequest;
import com.example.employeemanagement.model.EmployeeResponse;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.PositionRepository;
import com.example.employeemanagement.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void getEmployeeTest(){
        int id=1;
        Employee employee=Employee.builder().id(id).name("Ali").build();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));

        EmployeeResponse response=employeeService.getEmployee(id);

        assertThat(response).isNotNull();
        assertEquals(1,response.getId());
        assertEquals("Ali",response.getName());
    }
    @Test
    public void getEmployeeExceptionTest(){
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows( NotFoundException.class, ()-> employeeService.getEmployee(anyInt()));
    }

    @Test
    public void getAllEmployeeTest(){
        Employee employee=Employee.builder().id(1).name("Ali").build();
        Employee employee1=Employee.builder().id(2).name("Adil").build();
        when(employeeRepository.findAll()).thenReturn(List.of(employee1,employee));
        List<EmployeeResponse> allEmployee = employeeService.getAllEmployee();

        assertNotNull(allEmployee);
        assertThat(allEmployee.size()).isEqualTo(2);
    }

    @Test
    public void deleteEmployeeTest(){
        int id=1;

        willDoNothing().given(employeeRepository).deleteById(id);
        employeeService.deleteEmployee(id);

        verify(employeeRepository, times(1)).deleteById(id);
    }

    @Test
    public void saveEmployeeTest(){
        int id=1;
        EmployeeRequest request=EmployeeRequest.builder().name("qara").departmentId(1).surname("abdullayev").build();
        Employee employee=Employee.builder().id(id).name("qara").surname("abdullayev").build();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));

        Department department = Department.builder().id(1).name("IT").build();
        given(departmentRepository.findById(anyInt())).willReturn(Optional.of(department));

        Position position=Position.builder().id(1).name("Junior").salary(1200.0).build();
        given(positionRepository.findById(anyInt())).willReturn(Optional.of(position));

        given(employeeRepository.save(any(Employee.class))).willReturn(employee);
        EmployeeResponse response=employeeService.saveEmployee(request);

        assertNotNull(response);
        assertEquals("qara",response.getName());

    }
    @Test
    public void saveEmployeeExceptionTest(){
        EmployeeRequest request=EmployeeRequest.builder().name("qara").departmentId(1).surname("abdullayev").build();
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows( NotFoundException.class, ()-> employeeService.updateEmployee(1,request));
    }

    @Test
    public void saveEmployeeException2Test(){
        EmployeeRequest request=EmployeeRequest.builder().name("qara").departmentId(1).surname("abdullayev").build();
        Department department = Department.builder().id(1).name("IT").build();
        given(departmentRepository.findById(anyInt())).willReturn(Optional.of(department));
        given(positionRepository.findById(anyInt())).willReturn(Optional.empty());

        assertThrows( NotFoundException.class, ()-> employeeService.saveEmployee(request));
    }

    @Test
    public void updateEmployeeTest(){
        int id=1;
        EmployeeRequest request=EmployeeRequest.builder().name("qara").departmentId(1).surname("abdullayev").build();
        Employee employee=Employee.builder().id(id).name("Ali").surname("poladov").build();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));

        Department department = Department.builder().id(1).name("IT").build();
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(department));

        Position position=Position.builder().id(1).name("Junior").salary(1200.0).build();
        when(positionRepository.findById(anyInt())).thenReturn(Optional.of(position));

        given(employeeRepository.save(any(Employee.class))).willReturn(employee);
        EmployeeResponse response = employeeService.updateEmployee(id, request);

        assertNotNull(response);
        assertEquals("qara",response.getName());
    }

    @Test
    public void updateEmployeeException1Test(){
        int id=1;
        EmployeeRequest request=EmployeeRequest.builder().name("qara").departmentId(1).surname("abdullayev").build();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows( NotFoundException.class, ()-> employeeService.updateEmployee(id,request));
    }

    @Test
    public void updateEmployeeException2Test()  {
        int id=1;
        EmployeeRequest request=EmployeeRequest.builder().name("qara").departmentId(1).surname("abdullayev").build();
        Employee employee=Employee.builder().id(id).name("Ali").surname("poladov").build();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));

        when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows( NotFoundException.class, ()-> employeeService.updateEmployee(id,request));
    }

    @Test
    public void updateEmployeeException3Test()  {
        int id=1;
        EmployeeRequest request=EmployeeRequest.builder().name("qara").departmentId(1).surname("abdullayev").build();
        Employee employee=Employee.builder().id(id).name("Ali").surname("poladov").build();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));

        Department department = Department.builder().id(1).name("IT").build();
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(department));

        given(positionRepository.findById(anyInt())).willReturn(Optional.empty());

        assertThrows( NotFoundException.class, ()-> employeeService.updateEmployee(id,request));
    }
}
