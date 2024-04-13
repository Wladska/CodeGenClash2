package com.wladska.masters.experiment2.dtos;

import java.util.List;

public class DepartmentDTO {

    private Long id;
    private String name;
    private List<Long> employeeIds; // This will hold the IDs of the employees belonging to this department

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

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }
}