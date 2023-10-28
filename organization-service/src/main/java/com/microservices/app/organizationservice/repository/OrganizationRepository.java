package com.microservices.app.organizationservice.repository;

import com.microservices.app.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    public Organization findByOrgCode(String orgCode);
}
