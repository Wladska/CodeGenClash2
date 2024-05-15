package com.wladska.masters.experiment2.dtos;

import com.wladska.masters.experiment2.validators.DepartmentExists;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DepartmentDTO {
    @DepartmentExists
    private Long id;

    @NotNull(message = "Department name cannot be null")
    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    private String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}

