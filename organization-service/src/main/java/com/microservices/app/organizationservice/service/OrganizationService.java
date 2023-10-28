package com.microservices.app.organizationservice.service;

import com.microservices.app.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    public OrganizationDto saveOrganization(OrganizationDto organizationDto);
    public OrganizationDto getOrganizationByCode(String code);
}
