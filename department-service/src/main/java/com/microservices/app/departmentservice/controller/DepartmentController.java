package com.microservices.app.departmentservice.controller;

import com.microservices.app.departmentservice.dto.DepartmentDto;
import com.microservices.app.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") Long id){
        DepartmentDto retrievedDepartmentDto = departmentService.getDepartment(id);
        return new ResponseEntity<>(retrievedDepartmentDto, HttpStatus.OK);
    }

    @GetMapping("/code/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode){
        DepartmentDto retrievedDepartmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(retrievedDepartmentDto, HttpStatus.OK);
    }
}
