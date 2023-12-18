package com.example.employeemanagement.service;


import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.model.request.EmployeeRequest;
import com.example.employeemanagement.model.response.EmployeeResponse;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.impl.EmployeeServiceImpl;
import com.example.employeemanagement.service.impl.PositionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PositionServiceImpl positionService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void getEmployeeTest(){
        int id=1;
        Employee employee=Employee.builder().id(id).name("Ali").build();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));

        Optional<EmployeeResponse> response=employeeService.getEmployeeById(id);

        assertThat(response).isNotNull();
        assertEquals(1,response.get().id());
        assertEquals("Ali",response.get().name());
    }
    @Test
    public void getEmployeeExceptionTest(){
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows( EmployeeNotFoundException.class, ()-> employeeService.getEmployeeById(anyInt()));
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
        EmployeeRequest request= new EmployeeRequest("Qara","Abdullayev"
                ,"qara.abdullayev171@mail.com",1,true);
        Employee employee=Employee.builder().id(id).name("Qara").surname("Abdullayev").build();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));


        Position position=Position.builder().id(1).name("Junior").salary(1200.0).build();
        given(positionService.findById(anyInt())).willReturn(position);

        given(employeeRepository.save(any(Employee.class))).willReturn(employee);
        Optional<EmployeeResponse> response=employeeService.saveEmployee(request);

        assertNotNull(response);
        assertEquals("Qara",response.get().name());

    }

    @Test
    public void updateEmployeeTest(){
        int id=1;
        EmployeeRequest request= new EmployeeRequest("Qara","Abdullayev",
                "qara.abdullayev171@mail.com",1,true);
        Employee employee=Employee.builder().id(id).name("Ali").surname("Poladov").build();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));

        Position position=Position.builder().id(1).name("Junior").salary(1200.0).build();
        given(positionService.findById(anyInt())).willReturn(position);

        given(employeeRepository.save(any(Employee.class))).willReturn(employee);
        Optional<EmployeeResponse> response = employeeService.updateEmployee(id, request);

        assertNotNull(response);
        assertEquals("Qara",response.get().name());
    }

    @Test
    public void updateEmployeeException1Test(){
        int id=1;
        EmployeeRequest request= new EmployeeRequest("Qara","Abdullayev"
                ,"qara.abdullayev171@mail.com",1,true);
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows( EmployeeNotFoundException.class, ()-> employeeService.updateEmployee(id,request));
    }

}
