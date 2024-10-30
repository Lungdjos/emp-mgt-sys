package com.radix.ems.service.implementation;

import com.radix.ems.dto.EmployeeDto;
import com.radix.ems.exceptions.ResourceAlreadyExistException;
import com.radix.ems.exceptions.ResourceNotFoundException;
import com.radix.ems.mapper.EmployeeMapper;
import com.radix.ems.model.Employee;
import com.radix.ems.repos.EmployeeRepository;
import com.radix.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
           throw new ResourceAlreadyExistException("Employee already exists with email " + employeeDto.getEmail());
        }
    }

    /**
     * method to get employee by id
     * @param id
     * @return
     */
    @Override
    public EmployeeDto getEmployeeById(Long id) {
        var emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            return EmployeeMapper.employeeToEmployeeDto(emp.get());
        }else {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
    }

    /**
     * method to get all employees
     * @return
     */
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeesDto = employeeRepository.findAll()
                .stream().map(EmployeeMapper::employeeToEmployeeDto).toList();
        if (!employeesDto.isEmpty()) {
            return employeesDto;
        }else {
            throw new ResourceNotFoundException("No employees found");
        }
    }

    /**
     * method to delete employee
     * @param id
     */
    @Override
    public void deleteEmployee(Long id) {
        var emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            employeeRepository.delete(emp.get());
        }else {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
    }

    /**
     *  method to update employee details
     * @param id
     * @param employeeDto
     * @return
     */
    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        emp.setFirstName(employeeDto.getFirstName());
        emp.setLastName(employeeDto.getLastName());
        emp.setEmail(employeeDto.getEmail());
        emp.setMobile(employeeDto.getMobile());
        emp.setGender(employeeDto.getGender());
        emp.setDepartment(employeeDto.getDepartment());
        emp.setDesignation(employeeDto.getDesignation());

        return EmployeeMapper.employeeToEmployeeDto(employeeRepository.save(emp));
    }
}
