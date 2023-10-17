package com.microservices.app.departmentservice.service.impl;

import com.microservices.app.departmentservice.dto.DepartmentDto;
import com.microservices.app.departmentservice.entity.Department;
import com.microservices.app.departmentservice.mapper.DepartmentMapper;
import com.microservices.app.departmentservice.repository.DepartmentRepository;
import com.microservices.app.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto saveDDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);
        return saveDDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        Optional<Department> retrievedDepartment = departmentRepository.findById(id);
        DepartmentDto retrievedDepartmentDto = DepartmentMapper.mapToDepartmentDto(retrievedDepartment.get());
        return retrievedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department retrievedDepartment = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDto retrievedDepartmentDto = DepartmentMapper.mapToDepartmentDto(retrievedDepartment);
        return retrievedDepartmentDto;
    }
}
