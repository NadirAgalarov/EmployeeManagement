package com.example.employeemanagement.controller.service;

import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.NotFoundException;
import com.example.employeemanagement.model.PositionRequest;
import com.example.employeemanagement.model.PositionResponse;
import com.example.employeemanagement.repository.PositionRepository;
import com.example.employeemanagement.service.PositionService;
import com.example.employeemanagement.service.impl.PositionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
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
class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private PositionServiceImpl positionService;

    @Test
    public void getPositionTest(){
        int id=1;
        Position position=Position.builder().id(id).name("Junior").build();
        given(positionRepository.findById(id)).willReturn(Optional.<Position>of(position));

        PositionResponse positionResponse = positionService.getPosition(id);

        assertThat(positionResponse).isNotNull();
        assertEquals(1,positionResponse.getId());
        assertEquals("Junior",positionResponse.getName());
        assertDoesNotThrow(()-> new NotFoundException("not found"));

    }

    @DisplayName("Exceptin Test of getPosition method")
    @Test
    public void getPositionExceptionTest(){
        given(positionRepository.findById(anyInt())).willReturn(Optional.empty());

       assertThrows( NotFoundException.class, ()-> positionService.getPosition(anyInt()));
    }

    @Test
    public void savePositionTest(){
        int id=1;
        PositionRequest request=PositionRequest.builder().name("Junior").salary(1200.0).build();
        Position position=Position.builder().id(id).name("Junior").salary(1200.0).build();
        when(positionRepository.save(any(Position.class))).thenReturn(position);
        PositionResponse response=positionService.savePosition(request);

        assertNotNull(response);
        assertEquals(1,response.getId());
        assertEquals("Junior",response.getName());
    }
    @Test
    public void getAllPositionTest(){
        Position position=Position.builder().id(1).name("Junior").salary(1200.0).build();
        Position position1=Position.builder().id(2).name("senior").salary(2200.0).build();

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
        PositionRequest request=PositionRequest.builder().name("Senior").salary(1200.0).build();
        Position position=Position.builder().id(1).name("Junior").salary(2200.0).build();

        given(positionRepository.findById(id)).willReturn(Optional.<Position>of(position));
        when(positionRepository.save(any(Position.class))).thenReturn(position);

        PositionResponse response = positionService.updatePosition(request, id);

        assertNotNull(response);
    }

    @Test
    public void updatePositionExceptionTest(){
        int id=1;
        PositionRequest request=PositionRequest.builder().name("Senior").salary(1200.0).build();

        given(positionRepository.findById(any())).willReturn(Optional.empty());

        assertThrows( NotFoundException.class, ()-> positionService.updatePosition(request,id));
    }
}
