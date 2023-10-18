package com.microservices.app.employeeservice.service.impl;

import com.microservices.app.employeeservice.dto.APIResponseDto;
import com.microservices.app.employeeservice.dto.DepartmentDto;
import com.microservices.app.employeeservice.dto.EmployeeDto;
import com.microservices.app.employeeservice.entity.Employee;
import com.microservices.app.employeeservice.mapper.EmployeeMapper;
import com.microservices.app.employeeservice.repository.EmployeeRepository;
import com.microservices.app.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {
        Optional<Employee> retrievedEmployee = employeeRepository.findById(id);
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/code/" + retrievedEmployee.get().getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();
        EmployeeDto retrievedEmployeeDto = EmployeeMapper.mapToEmployeeDto(retrievedEmployee.get());
        APIResponseDto apiResponseDto = new APIResponseDto(retrievedEmployeeDto, departmentDto);
        return apiResponseDto;
    }
}
