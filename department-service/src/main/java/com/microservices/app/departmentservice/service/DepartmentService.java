package com.microservices.app.departmentservice.service;

import com.microservices.app.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartment(Long id);
    DepartmentDto getDepartmentByCode(String departmentCode);
}
