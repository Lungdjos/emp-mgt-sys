package com.radix.ems.mapper;

import com.radix.ems.dto.EmployeeDto;
import com.radix.ems.model.Employee;

public class EmployeeMapper {
    /**
     *
     * @param employee
     * @return
     */
    public static EmployeeDto employeeToEmployeeDto(Employee employee) {

        return new EmployeeDto(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getMobile(),
                employee.getGender(),
                employee.getDepartment(),
                employee.getDesignation()
        );
    }

    /**
     *
     * @param employeeDto
     * @return
     */
    public static Employee employeeDtoToEmployee(EmployeeDto employeeDto) {

        return new Employee(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getMobile(),
                employeeDto.getGender(),
                employeeDto.getDepartment(),
                employeeDto.getDesignation()
        );
    }
}
