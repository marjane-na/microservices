package com.microservices.app.organizationservice.mapper;

import com.microservices.app.organizationservice.dto.OrganizationDto;
import com.microservices.app.organizationservice.entity.Organization;

public class OrganizationMapper {
    public static Organization mapToOrganization(OrganizationDto organizationDto){
        Organization organization = new Organization(
                organizationDto.getId(),
                organizationDto.getName(),
                organizationDto.getDescription(),
                organizationDto.getOrgCode(),
                organizationDto.getOrgCreated()
        );
        return organization;
    }

    public static OrganizationDto mapToOrganizationDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto(
                organization.getId(),
                organization.getName(),
                organization.getDescription(),
                organization.getOrgCode(),
                organization.getOrgCreated()
        );
        return organizationDto;
    }
}
