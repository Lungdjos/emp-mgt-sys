package com.radix.ems.controller;

import com.radix.ems.dto.EmployeeDto;
import com.radix.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestCtrl {
    @Autowired
    private EmployeeService employeeService;

    /**
     *
     * @param employeeDto
     * @return
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
        try {
            return  new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FOUND);
        }
    }
}
