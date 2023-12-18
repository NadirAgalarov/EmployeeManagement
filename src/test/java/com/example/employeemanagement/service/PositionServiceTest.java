package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.PositonNotFoundException;
import com.example.employeemanagement.model.request.PositionRequest;
import com.example.employeemanagement.model.response.PositionResponse;
import com.example.employeemanagement.repository.PositionRepository;
import com.example.employeemanagement.service.impl.DepartmentServiceImpl;
import com.example.employeemanagement.service.impl.PositionServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@SpringBootTest
class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;
    @Mock
    private DepartmentServiceImpl departmentService;
    @InjectMocks
    private PositionServiceImpl positionService;

    @Test
    public void getPositionTest(){
        int id=1;
        Position position=Position.builder().id(id).name("Junior").build();
        given(positionRepository.findById(id)).willReturn(Optional.<Position>of(position));

        Optional<PositionResponse> positionResponse = positionService.getPositionById(id);

        assertThat(positionResponse).isNotNull();
        assertEquals(1,positionResponse.get().id());
        assertEquals("Junior",positionResponse.get().name());
        assertDoesNotThrow(()-> new PositonNotFoundException("Position not found"));

    }

    @Test
    public void getPositionExceptionTest(){
        given(positionRepository.findById(anyInt())).willReturn(Optional.empty());

       assertThrows( PositonNotFoundException.class, ()-> positionService.getPositionById(anyInt()));
    }

    @Test
    public void savePositionTest(){
        int id=1;
        PositionRequest request=new PositionRequest("Junior",1200.0,1);
        Department department=Department.builder().id(id).name("IT").build();
        Position position=Position.builder().id(id).name("Junior").salary(1200.0)
                .department(department).build();

        when(departmentService.findById(anyInt())).thenReturn(department);;
        when(positionRepository.save(any(Position.class))).thenReturn(position);

        Optional<PositionResponse> response=positionService.savePosition(request);

        assertNotNull(response);
        assertEquals(1,response.get().id());
        assertEquals("Junior",response.get().name());
    }
    @Test
    public void getAllPositionTest(){
        Position position=Position.builder().id(1).name("Junior").salary(1200.0).build();
        Position position1=Position.builder().id(2).name("Senior").salary(2200.0).build();

        when(positionRepository.findAll()).thenReturn(Arrays.asList(position,position1));
        List<PositionResponse> allPosition = positionService.getAllPosition();

        assertNotNull(allPosition);
        assertThat(allPosition.size()).isEqualTo(2);
    }

    @Test
    public void deletePositionTest(){
        int id=1;

        willDoNothing().given(positionRepository).deleteById(id);
        positionService.deletePosition(id);

        verify(positionRepository, times(1)).deleteById(id);
    }
    @Test
    public  void updatePositionTest(){
        int id=1;
        PositionRequest request=new PositionRequest("Senior",1200.0,1);
        Department department=Department.builder().id(id).name("IT").build();

        Position position=Position.builder().id(1).name("Junior").salary(2200.0)
                .department(department).build();
        given(positionRepository.findById(id)).willReturn(Optional.<Position>of(position));


        when(departmentService.findById(anyInt())).thenReturn(department);

        when(positionRepository.save(any(Position.class))).thenReturn(position);

        Optional<PositionResponse> response = positionService.updatePosition(request, id);

        assertNotNull(response);
    }

    @Test
    public void updatePositionExceptionTest(){
        int id=1;
        PositionRequest request=new PositionRequest("Senior",1200.0,1);

        given(positionRepository.findById(any())).willReturn(Optional.empty());

        assertThrows( PositonNotFoundException.class, ()-> positionService.updatePosition(request,id));
    }

    @Test
    public void findByIdTest(){
        int id=1;
        Position position=Position.builder().id(id).name("Junior").build();
        given(positionRepository.findById(id)).willReturn(Optional.<Position>of(position));

        Position position1 = positionService.findById(id);

        assertThat(position1).isNotNull();
        assertEquals(1,position1.getId());
        assertEquals("Junior",position1.getName());
        assertDoesNotThrow(()-> new PositonNotFoundException("Position not found"));

    }
    @Test
    public void findByIdExceptionTest(){
        given(positionRepository.findById(anyInt())).willReturn(Optional.empty());

        assertThrows( PositonNotFoundException.class, ()-> positionService.findById(anyInt()));
    }
}
