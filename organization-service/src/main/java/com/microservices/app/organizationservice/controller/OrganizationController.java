package com.microservices.app.organizationservice.controller;

import com.microservices.app.organizationservice.dto.OrganizationDto;
import com.microservices.app.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String code){
        OrganizationDto retrievedOrganizationDto = organizationService.getOrganizationByCode(code);
        return new ResponseEntity<>(retrievedOrganizationDto, HttpStatus.OK);
    }
}
