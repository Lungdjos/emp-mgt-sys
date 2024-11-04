package com.radix.ems.controller;

import com.radix.ems.dto.EmployeeDto;
import com.radix.ems.model.Employee;
import com.radix.ems.service.EmployeeService;
import com.radix.ems.utilities.ApiResponse;
import com.radix.ems.utilities.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeRestCtrl {

    private final EmployeeService employeeService;

    /**
     * controller method to create employee
     * @param employeeDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDto>> createEmployee(@RequestBody EmployeeDto employeeDto) {
        // Call the service method to create the employee
        EmployeeDto employee = employeeService.createEmployee(employeeDto);

        // Wrap the result in ApiResponse
        ApiResponse<EmployeeDto> apiResponse = ResponseUtil.success(employee, "Employee created successfully", "/api/v1/employees");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


    /**
     * Controller method to get employee by ID
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<EmployeeDto>> getEmployee(@PathVariable("id") Long id) {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        ApiResponse<EmployeeDto> apiResponse = ResponseUtil.success(employee, "Employee retrieved successfully", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Controller method to get all employees
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDto>>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        ApiResponse<List<EmployeeDto>> apiResponse = ResponseUtil.success(employees, "Employees retrieved successfully", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Controller method to update employee
     * @param id
     * @param employeeDto
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<EmployeeDto>> updateEmployee(@PathVariable("id") Long id,
                                                                   @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        ApiResponse<EmployeeDto> apiResponse = ResponseUtil.success(updatedEmployee, "Employee updated successfully", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Controller method to delete employee
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        ApiResponse<String> apiResponse = ResponseUtil.success("Employee deleted successfully!", "Employee deleted successfully", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
