package com.radix.ems.service;

import com.radix.ems.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    void deleteEmployee(Long id);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
}
