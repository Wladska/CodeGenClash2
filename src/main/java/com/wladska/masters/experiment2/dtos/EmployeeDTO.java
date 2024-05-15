package com.wladska.masters.experiment2.dtos;

import com.wladska.masters.experiment2.validators.DepartmentExists;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeDTO {
    private Long id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")

    private String name;
    @NotNull(message = "Position cannot be null")
    @Size(min = 2, max = 50, message = "Position must be between 2 and 50 characters")
    private String position;
    @NotNull(message = "Salary cannot be null")
    private double salary;
    @Valid // This ensures that validations in DepartmentDTO are also applied
    @NotNull(message = "Department information is required")
    private DepartmentDTO department;  // Use DepartmentDTO instead of Long departmentId

    public EmployeeDTO() {
    }

    // Constructor with all parameters, if necessary
    public EmployeeDTO(Long id, String name, String position, double salary, DepartmentDTO department) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.department = department;
    }

    // Getters and setters
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }
}
