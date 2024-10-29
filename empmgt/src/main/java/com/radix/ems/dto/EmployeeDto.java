package com.radix.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    // attributes
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String gender;
    private String department;
    private String designation;
}
