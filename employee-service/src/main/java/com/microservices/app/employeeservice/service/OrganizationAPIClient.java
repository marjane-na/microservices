package com.microservices.app.employeeservice.service;

import com.microservices.app.employeeservice.dto.DepartmentDto;
import com.microservices.app.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "ORGANIZATION-SERVICE")
@FeignClient(url = "http://localhost:8083", value = "ORGANIZATION-SERVICE")
public interface OrganizationAPIClient {
    @GetMapping("api/organizations/code/{org-code}")
    OrganizationDto getOrganization(@PathVariable("org-code") String orgCode);
}
