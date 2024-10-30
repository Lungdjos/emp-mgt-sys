package com.radix.ems.service;

import com.radix.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getAllEmployees();
    void deleteEmployee(Long id);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
}
