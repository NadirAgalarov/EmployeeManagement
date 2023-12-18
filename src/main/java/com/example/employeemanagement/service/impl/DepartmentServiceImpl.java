package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.exception.DepartmentNotFoundException;
import com.example.employeemanagement.mapper.DepartmentMapper;
import com.example.employeemanagement.model.request.DepartmentRequest;
import com.example.employeemanagement.model.response.DepartmentResponse;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Override
    public Optional<DepartmentResponse> saveDepartment(DepartmentRequest request) {
        LOGGER.info("ActionLog.saveDepartment.start request: {}",request);
        Department department= DepartmentMapper.INSTANCE.requestToEntity(request);
        Department savedDepartment=departmentRepository.save(department);
        DepartmentResponse response = DepartmentMapper.INSTANCE.entityToResponse(savedDepartment);
        LOGGER.info("ActionLog.saveDepartment.end response: {}",response);
        return Optional.of(response);
    }

    @Override
    public Optional<DepartmentResponse> getDepartmentById(int id) {
        LOGGER.info("ActionLog.getDepartment.start id: {}",id);
        Department department=departmentRepository.findById(id).orElseThrow(
                ()-> new DepartmentNotFoundException("Departament not foumd for id: "+id));
        DepartmentResponse response = DepartmentMapper.INSTANCE.entityToResponse(department);
        LOGGER.info("ActionLog.getDepartment.end response: {}",response);
        return Optional.of(response);
    }

    @Override
    public List<DepartmentResponse> getAllDepartment() {
        LOGGER.info("ActionLog.getAllDepartment.start");
        List<Department> departments=departmentRepository.findAll();
        List<DepartmentResponse> responses = DepartmentMapper.INSTANCE.entityListToResponseList(departments);
        LOGGER.info("ActionLog.getAllDepartment.end Department count: {}",responses.size());
        return responses;
    }

    @Override
    public Optional<DepartmentResponse> updateDepartment(int id, DepartmentRequest request) {
        LOGGER.info("ActionLog.updateDepartment.start id: {}",id);
        Department department=departmentRepository.findById(id).orElseThrow(
                ()->new DepartmentNotFoundException("Department not found for id"+id));
        DepartmentMapper.INSTANCE.updateEntity(department,request);
        Department savedDepartment=departmentRepository.save(department);
        DepartmentResponse response=DepartmentMapper.INSTANCE.entityToResponse(savedDepartment);
        LOGGER.info("ActionLog.updateDepartment.end response: {}",response);
        return Optional.of(response);
    }

    @Override
    public ResponseEntity<?> deleteDepartment(int id) {
        LOGGER.info("ActionLog.deleteDepartment.start id: {}",id);
        departmentRepository.deleteById(id);
        LOGGER.info("ActionLog.deleteDepartment.end successfully");
        return ResponseEntity.ok().build();
    }

    @Override
    public Department findById(int id) {
        LOGGER.info("ActionLog.findById.start id: {}",id);

        Department department=departmentRepository.findById(id).orElseThrow(
                ()-> new DepartmentNotFoundException("Departament not foumd for id: "+id));

        LOGGER.info("ActionLog.findById.end response: {}",department);
        return department;
    }
}
