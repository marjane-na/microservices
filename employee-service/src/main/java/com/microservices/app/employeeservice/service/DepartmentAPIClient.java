package com.microservices.app.employeeservice.service;

import com.microservices.app.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentAPIClient {

    @GetMapping("api/departments/code/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
