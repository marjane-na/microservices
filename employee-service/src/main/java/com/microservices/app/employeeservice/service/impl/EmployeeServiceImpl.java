package com.microservices.app.employeeservice.service.impl;

import com.microservices.app.employeeservice.dto.APIResponseDto;
import com.microservices.app.employeeservice.dto.DepartmentDto;
import com.microservices.app.employeeservice.dto.EmployeeDto;
import com.microservices.app.employeeservice.entity.Employee;
import com.microservices.app.employeeservice.mapper.EmployeeMapper;
import com.microservices.app.employeeservice.repository.EmployeeRepository;
import com.microservices.app.employeeservice.service.APIClient;
import com.microservices.app.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    //private RestTemplate restTemplate;
    //private WebClient webClient;
    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long id) {
        Optional<Employee> retrievedEmployee = employeeRepository.findById(id);
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/code/" + retrievedEmployee.get().getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/code/" + retrievedEmployee.get().getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        DepartmentDto departmentDto = apiClient.getDepartment(retrievedEmployee.get().getDepartmentCode());
        EmployeeDto retrievedEmployeeDto = EmployeeMapper.mapToEmployeeDto(retrievedEmployee.get());
        APIResponseDto apiResponseDto = new APIResponseDto(retrievedEmployeeDto, departmentDto);
        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long id, Exception exception) {
        Employee retrievedEmployee = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Operations Department");
        departmentDto.setDepartmentCode("OP001");
        departmentDto.setDepartmentDescription("Central Department of Operations");

        EmployeeDto employeeDto = new EmployeeDto(
                retrievedEmployee.getId(),
                retrievedEmployee.getFirstName(),
                retrievedEmployee.getLastName(),
                retrievedEmployee.getEmail(),
                retrievedEmployee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }
}
