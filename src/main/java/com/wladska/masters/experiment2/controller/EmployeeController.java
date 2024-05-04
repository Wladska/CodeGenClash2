package com.wladska.masters.experiment2.controller;

import com.wladska.masters.experiment2.model.Employee;
import com.wladska.masters.experiment2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    // Additional methods to handle CRUD operations
}
