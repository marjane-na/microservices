package com.microservices.app.employeeservice.service;

import com.microservices.app.employeeservice.dto.APIResponseDto;
import com.microservices.app.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long id);
}
