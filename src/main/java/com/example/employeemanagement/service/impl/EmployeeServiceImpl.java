package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.NotFoundException;
import com.example.employeemanagement.mapper.EmployeeMapper;
import com.example.employeemanagement.model.EmployeeRequest;
import com.example.employeemanagement.model.EmployeeResponse;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.PositionRepository;
import com.example.employeemanagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private  final EmployeeRepository employeeRepository;
    private  final DepartmentRepository departmentRepository;
    private  final PositionRepository positionRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest request) {
        LOGGER.info("ActionLog.saveEmployee.start request: {}",request);
        Employee employee= EmployeeMapper.INSTANCE.requestToEntity(request);
        Department department=departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(()-> new NotFoundException("Department not found for id: "+request.getDepartmentId()));
        employee.setDepartment(department);

        Position position=positionRepository.findById(request.getPositionId()).orElseThrow(
                ()-> new NotFoundException("Position not found for id: "+request.getPositionId()));
        employee.setPosition(position);
        Employee savedEmployee= employeeRepository.save(employee);
        EmployeeResponse response = EmployeeMapper.INSTANCE.entityToResponse(savedEmployee);
        LOGGER.info("ActionLog.saveEmployee.end response: {}",response);
        return response;
  }

    @Override
    public EmployeeResponse getEmployee(int id) {
        LOGGER.info("ActionLog.getEmployee.start id: {}",id);
        Employee employee=employeeRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Employee not found fr id: "+id));
        EmployeeResponse response =EmployeeMapper.INSTANCE.entityToResponse(employee);
        LOGGER.info("ActionLog.getEmployee.end response: {}",response);
        return response;
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
    public EmployeeResponse updateEmployee(int id, EmployeeRequest request) {
        LOGGER.info("ActionLog.updateEmployee.start id: {}",id);
        Employee employee=employeeRepository.findById(id).orElseThrow(
                ()->new NotFoundException("Employee not found for id: "+id));
        Department department=departmentRepository.findById(request.getDepartmentId()).orElseThrow(
                ()->new NotFoundException("Department not found for id: "+request.getDepartmentId()));
        employee.setDepartment(department);
        Position position=positionRepository.findById(request.getPositionId()).orElseThrow(
                ()->new NotFoundException("Position not found for id: "+request.getDepartmentId()));
        employee.setPosition(position);
        EmployeeMapper.INSTANCE.updateEntity(employee,request);
        Employee savedEmployee=employeeRepository.save(employee);
        EmployeeResponse response=EmployeeMapper.INSTANCE.entityToResponse(savedEmployee);
        LOGGER.info("ActionLog.updateEmployee.end response: {}",response);
        return response;
    }

    @Override
    public ResponseEntity<?> deleteEmployee(int id) {
        LOGGER.info("ActionLog.deleteEmployee.start id: {}",id);
        employeeRepository.deleteById(id);
        LOGGER.info("ActionLog.deleteEmployee.end id: {}",id);
        return ResponseEntity.ok().build();
    }
}
