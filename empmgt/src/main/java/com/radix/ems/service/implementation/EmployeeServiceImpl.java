package com.radix.ems.service.implementation;

import com.radix.ems.dto.EmployeeDto;
import com.radix.ems.mapper.EmployeeMapper;
import com.radix.ems.repos.EmployeeRepository;
import com.radix.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * method to create employee
     * @param employeeDto
     * @return
     */
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // validation on the new eployee
        var emp = employeeRepository.findByEmail(employeeDto.getEmail());
        if (emp == null) {
            var emp1 = employeeRepository.save(EmployeeMapper.employeeDtoToEmployee(employeeDto));
            return EmployeeMapper.employeeToEmployeeDto(emp1);
        } else {
           throw new RuntimeException("Employee already exists");
        }
    }

    /**
     * method to delete employee
     * @param id
     */
    @Override
    public void deleteEmployee(Long id) {

    }

    /**
     *  method to update employee details
     * @param id
     * @param employeeDto
     * @return
     */
    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        return null;
    }
}
