package com.radix.ems.service.implementation;

import com.radix.ems.dto.EmployeeDto;
import com.radix.ems.exceptions.ResourceAlreadyExistException;
import com.radix.ems.exceptions.ResourceNotFoundException;
import com.radix.ems.mapper.EmployeeMapper;
import com.radix.ems.model.Employee;
import com.radix.ems.repos.EmployeeRepository;
import com.radix.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Method to create a new employee.
     * Checks if an employee with the given email already exists.
     * If not, saves the new employee and returns the created EmployeeDto.
     *
     * @param employeeDto The data transfer object containing employee details.
     * @return The created EmployeeDto.
     */
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        log.info("Attempting to create employee with email: {}", employeeDto.getEmail());

        var emp = employeeRepository.findByEmail(employeeDto.getEmail());
        if (emp == null) {
            var emp1 = employeeRepository.save(EmployeeMapper.employeeDtoToEmployee(employeeDto));
            log.info("Employee created successfully with email: {}", emp1.getEmail());
            return EmployeeMapper.employeeToEmployeeDto(emp1);
        } else {
            log.error("Employee already exists with email: {}", employeeDto.getEmail());
            throw new ResourceAlreadyExistException("Employee already exists with email " + employeeDto.getEmail());
        }
    }

    /**
     * Method to retrieve an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The EmployeeDto corresponding to the provided ID.
     */
    @Override
    public EmployeeDto getEmployeeById(Long id) {
        log.info("Retrieving employee with id: {}", id);

        var emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            log.info("Employee found with id: {}", id);
            return EmployeeMapper.employeeToEmployeeDto(emp.get());
        } else {
            log.error("Employee not found with id: {}", id);
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
    }

    /**
     * Method to retrieve all employees.
     *
     * @return A list of EmployeeDto for all employees.
     */
    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.info("Retrieving all employees");

        List<EmployeeDto> employeesDto = employeeRepository.findAll()
                .stream().map(EmployeeMapper::employeeToEmployeeDto).toList();
        if (!employeesDto.isEmpty()) {
            log.info("Total employees found: {}", employeesDto.size());
            return employeesDto;
        } else {
            log.warn("No employees found");
            throw new ResourceNotFoundException("No employees found");
        }
    }

    /**
     * Method to delete an employee by their ID.
     *
     * @param id The ID of the employee to delete.
     */
    @Override
    public void deleteEmployee(Long id) {
        log.info("Attempting to delete employee with id: {}", id);

        var emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            employeeRepository.delete(emp.get());
            log.info("Employee deleted successfully with id: {}", id);
        } else {
            log.error("Employee not found with id : {}", id);
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
    }

    /**
     * Method to update employee details.
     *
     * @param id The ID of the employee to update.
     * @param employeeDto The data transfer object containing updated employee details.
     * @return The updated EmployeeDto.
     */
    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        log.info("Updating employee with id: {}", id);

        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));

        // Update employee fields with new values
        emp.setFirstName(employeeDto.getFirstName());
        emp.setLastName(employeeDto.getLastName());
        emp.setEmail(employeeDto.getEmail());
        emp.setMobile(employeeDto.getMobile());
        emp.setGender(employeeDto.getGender());
        emp.setDepartment(employeeDto.getDepartment());
        emp.setDesignation(employeeDto.getDesignation());

        EmployeeDto updatedEmployeeDto = EmployeeMapper.employeeToEmployeeDto(employeeRepository.save(emp));
        log.info("Employee updated successfully with id: {}", id);

        return updatedEmployeeDto;
    }
}

