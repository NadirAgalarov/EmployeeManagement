package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.mapper.EmployeeMapper;
import com.example.employeemanagement.model.request.EmployeeRequest;
import com.example.employeemanagement.model.response.EmployeeResponse;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private  final EmployeeRepository employeeRepository;
    private  final PositionService positionService;
    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public Optional<EmployeeResponse> saveEmployee(EmployeeRequest request) {
        LOGGER.info("ActionLog.saveEmployee.start request: {}",request);
        Employee employee= EmployeeMapper.INSTANCE.requestToEntity(request);
        Position position=positionService.findById(employee.getPosition().getId());
        employee.setPosition(position);
        Employee savedEmployee= employeeRepository.save(employee);
        EmployeeResponse response = EmployeeMapper.INSTANCE.entityToResponse(savedEmployee);
        LOGGER.info("ActionLog.saveEmployee.end response: {}",response);
        return Optional.of(response);
  }

    @Override
    public Optional<EmployeeResponse> getEmployeeById(int id) {
        LOGGER.info("ActionLog.getEmployee.start id: {}",id);
        Employee employee=employeeRepository.findById(id).orElseThrow(
                ()-> new EmployeeNotFoundException("Employee not found fr id: "+id));
        EmployeeResponse response =EmployeeMapper.INSTANCE.entityToResponse(employee);
        LOGGER.info("ActionLog.getEmployee.end response: {}",response);
        return Optional.of(response);
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        LOGGER.info("ActionLog.getAllEmployee.start ");
        List<Employee> employees=employeeRepository.findAll();
        List<EmployeeResponse> responses = EmployeeMapper.INSTANCE.entityListToResponseList(employees);
        LOGGER.info("ActionLog.getAllEmployee.end Employee count: "+responses.size());
        return responses;
    }

    @Override
    public Optional<EmployeeResponse> updateEmployee(int id, EmployeeRequest request) {
        LOGGER.info("ActionLog.updateEmployee.start id: {}",id);

        Employee employee=employeeRepository.findById(id).orElseThrow(
                ()->new EmployeeNotFoundException("Employee not found for id: "+id));
        if(request.positionId()!=null){
        Position position=positionService.findById(request.positionId());
        employee.setPosition(position);}

        EmployeeMapper.INSTANCE.updateEntity(employee,request);
        Employee savedEmployee=employeeRepository.save(employee);
        EmployeeResponse response=EmployeeMapper.INSTANCE.entityToResponse(savedEmployee);
        LOGGER.info("ActionLog.updateEmployee.end response: {}",response);
        return Optional.of(response);
    }

    @Override
    public ResponseEntity<?> deleteEmployee(int id) {
        LOGGER.info("ActionLog.deleteEmployee.start id: {}",id);
        employeeRepository.deleteById(id);
        LOGGER.info("ActionLog.deleteEmployee.end id: {}",id);
        return ResponseEntity.ok().build();
    }
}
