package com.radix.ems.service;

import com.radix.ems.dto.EmployeeDto;
import com.radix.ems.exceptions.ResourceAlreadyExistException;
import com.radix.ems.exceptions.ResourceNotFoundException;
import com.radix.ems.model.Employee;
import com.radix.ems.repos.EmployeeRepository;
import com.radix.ems.service.implementation.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    void createEmployee_ShouldReturnEmployeeDto_WhenEmailDoesNotExist() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto(1L,"John", "Doe", "john.doe@example.com", "1234567890", "Male", "IT", "Developer");
        when(employeeRepository.findByEmail(employeeDto.getEmail())).thenReturn(null);
        when(employeeRepository.save(any())).thenReturn(new Employee("John", "Doe", "john.doe@example.com", "1234567890", "Male", "IT", "Developer"));

        // Act
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);

        // Assert
        assertNotNull(createdEmployee);
        assertEquals(employeeDto.getEmail(), createdEmployee.getEmail());
        verify(employeeRepository).save(any());
    }

    @Test
    void createEmployee_ShouldThrowException_WhenEmailExists() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto(1L,"John", "Doe", "john.doe@example.com", "1234567890", "Male", "IT", "Developer");
        when(employeeRepository.findByEmail(employeeDto.getEmail())).thenReturn(new Employee());

        // Act & Assert
        assertThrows(ResourceAlreadyExistException.class, () -> employeeService.createEmployee(employeeDto));
    }

    @Test
    void getEmployeeById_ShouldReturnEmployeeDto_WhenEmployeeExists() {
        // Arrange
        Long id = 0L;
        Employee employee = new Employee("John", "Doe", "john.doe@example.com", "1234567890", "Male", "IT", "Developer");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // Act
        EmployeeDto foundEmployee = employeeService.getEmployeeById(id);

        // Assert
        assertNotNull(foundEmployee);
        assertEquals(id, foundEmployee.getId()); // This is failing
    }


    @Test
    void getEmployeeById_ShouldThrowException_WhenEmployeeDoesNotExist() {
        // Arrange
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(id));
    }

    @Test
    void getAllEmployees_ShouldReturnListOfEmployeeDto_WhenEmployeesExist() {
        // Arrange
        List<Employee> employees = List.of(new Employee("John", "Doe", "john.doe@example.com", "1234567890", "Male", "IT", "Developer"));
        when(employeeRepository.findAll()).thenReturn(employees);

        // Act
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();

        // Assert
        assertFalse(allEmployees.isEmpty());
        assertEquals(employees.size(), allEmployees.size());
    }

    @Test
    void getAllEmployees_ShouldThrowException_WhenNoEmployeesExist() {
        // Arrange
        when(employeeRepository.findAll()).thenReturn(List.of());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeService.getAllEmployees());
    }

    @Test
    void deleteEmployee_ShouldDeleteEmployee_WhenEmployeeExists() {
        // Arrange
        Long id = 1L;
        Employee employee = new Employee("John", "Doe", "john.doe@example.com", "1234567890", "Male", "IT", "Developer");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // Act
        employeeService.deleteEmployee(id);

        // Assert
        verify(employeeRepository).delete(employee);
    }

    @Test
    void deleteEmployee_ShouldThrowException_WhenEmployeeDoesNotExist() {
        // Arrange
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(id));
    }

    @Test
    void updateEmployee_ShouldReturnUpdatedEmployeeDto_WhenEmployeeExists() {
        // Arrange
        Long id = 1L;
        Employee existingEmployee = new Employee("John", "Doe", "john.doe@example.com", "1234567890", "Male", "IT", "Developer");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        EmployeeDto updateDto = new EmployeeDto(id,"Jane", "Doe", "jane.doe@example.com", "0987654321", "Female", "HR", "Manager");
        when(employeeRepository.save(any())).thenReturn(new Employee("Jane", "Doe", "jane.doe@example.com", "0987654321", "Female", "HR", "Manager"));

        // Act
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, updateDto);

        // Assert
        assertNotNull(updatedEmployee);
        assertEquals(updateDto.getEmail(), updatedEmployee.getEmail());
    }

    @Test
    void updateEmployee_ShouldThrowException_WhenEmployeeDoesNotExist() {
        // Arrange
        Long id = 1L;
        EmployeeDto updateDto = new EmployeeDto(id, "Jane", "Doe", "jane.doe@example.com", "0987654321", "Female", "HR", "Manager");
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployee(id, updateDto));
    }

}
