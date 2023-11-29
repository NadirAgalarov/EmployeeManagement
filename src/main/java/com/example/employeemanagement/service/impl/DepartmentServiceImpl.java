package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Position;
import com.example.employeemanagement.exception.NotFoundException;
import com.example.employeemanagement.mapper.DepartmentMapper;
import com.example.employeemanagement.model.DepartmentRequest;
import com.example.employeemanagement.model.DepartmentResponse;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.PositionRepository;
import com.example.employeemanagement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Override
    public DepartmentResponse saveDepartment(DepartmentRequest request) {
        LOGGER.info("ActionLog.saveDepartment.start request: {}",request);
        Department department= DepartmentMapper.INSTANCE.requestToEntity(request);
        List<Integer> ids=request.getPositionIds();
        List<Position> positionSet =positionRepository.findAllById(ids);
        department.setPositions(positionSet);
        Department savedDepartment=departmentRepository.save(department);
        DepartmentResponse response = DepartmentMapper.INSTANCE.entityToResponse(savedDepartment);
        LOGGER.info("ActionLog.saveDepartment.end response: {}",response);
        return response;
    }

    @Override
    public DepartmentResponse getDepartment(int id) {
        LOGGER.info("ActionLog.getDepartment.start id: {}",id);
        Department department=departmentRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Departament not foumd for id: "+id));
        DepartmentResponse response = DepartmentMapper.INSTANCE.entityToResponse(department);
        LOGGER.info("ActionLog.getDepartment.end response: {}",response);
        return response;
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
    public DepartmentResponse updateDepartment(int id, DepartmentRequest request) {
        LOGGER.info("ActionLog.updateDepartment.start id: {}",id);
        Department department=departmentRepository.findById(id).orElseThrow(
                ()->new NotFoundException("Department not found for id"+id));
        DepartmentMapper.INSTANCE.updateEntity(department,request);
        List<Integer> ids=request.getPositionIds();
        List<Position> positionSet =positionRepository.findAllById(ids);
        department.setPositions(positionSet);
        Department savedDepartment=departmentRepository.save(department);
        DepartmentResponse response=DepartmentMapper.INSTANCE.entityToResponse(savedDepartment);
        LOGGER.info("ActionLog.updateDepartment.end response: {}",response);
        return response;
    }

    @Override
    public ResponseEntity<?> deleteDepartment(int id) {
        LOGGER.info("ActionLog.deleteDepartment.start id: {}",id);
        departmentRepository.deleteById(id);
        LOGGER.info("ActionLog.deleteDepartment.end successfully");
        return ResponseEntity.ok().build();
    }
}
