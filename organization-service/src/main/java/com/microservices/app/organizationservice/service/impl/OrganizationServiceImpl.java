package com.microservices.app.organizationservice.service.impl;

import com.microservices.app.organizationservice.dto.OrganizationDto;
import com.microservices.app.organizationservice.entity.Organization;
import com.microservices.app.organizationservice.mapper.OrganizationMapper;
import com.microservices.app.organizationservice.repository.OrganizationRepository;
import com.microservices.app.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        OrganizationDto organizationDto1 = OrganizationMapper.mapToOrganizationDto(savedOrganization);
        return organizationDto1;
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Organization retrievedOrganization = organizationRepository.findByOrgCode(code);
        OrganizationDto organizationDto = OrganizationMapper.mapToOrganizationDto(retrievedOrganization);
        return organizationDto;
    }
}
