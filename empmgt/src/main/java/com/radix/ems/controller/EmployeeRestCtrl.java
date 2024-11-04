package com.radix.ems.controller;

import com.radix.ems.dto.EmployeeDto;
import com.radix.ems.service.EmployeeService;
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
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            return  new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * controller method to get employee by id
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long id) {
        try {
            return  new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * controller method to get all employees
     * @return
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        try {
            return  new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * controller method to update employee
     * @param id
     * @param employeeDto
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id,
                                                      @RequestBody EmployeeDto employeeDto) {
        try {
            return  new ResponseEntity<>(employeeService.updateEmployee(id, employeeDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * controller method to delete employee
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }
}
