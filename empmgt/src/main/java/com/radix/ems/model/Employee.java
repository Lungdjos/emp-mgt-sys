package com.radix.ems.model;

import com.radix.ems.model.abstracts.AbstractEnity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends AbstractEnity {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String mobile;
    private String gender;
    private String department;
    private String designation;
}
