package com.microservices.app.employeeservice.service.impl;

import com.microservices.app.employeeservice.dto.EmployeeDto;
import com.microservices.app.employeeservice.entity.Employee;
import com.microservices.app.employeeservice.mapper.EmployeeMapper;
import com.microservices.app.employeeservice.repository.EmployeeRepository;
import com.microservices.app.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Optional<Employee> retrievedEmployee = employeeRepository.findById(id);
        EmployeeDto retrievedEmployeeDto = EmployeeMapper.mapToEmployeeDto(retrievedEmployee.get());
        return retrievedEmployeeDto;
    }
}
