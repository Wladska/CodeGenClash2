package com.wladska.masters.experiment2.service;

import com.wladska.masters.experiment2.exception.ResourceNotFoundException;
import com.wladska.masters.experiment2.model.Employee;
import com.wladska.masters.experiment2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Additional methods to handle CRUD operations

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id " + id)
        );
    }
}