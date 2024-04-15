package com.wladska.masters.experiment2.dtos;

import com.wladska.masters.experiment2.validators.DepartmentExists;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Department cannot be null")
    @DepartmentExists
    private Long department;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }
}