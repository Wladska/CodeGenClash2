package com.wladska.masters.experiment2.controller;

import com.wladska.masters.experiment2.dtos.DepartmentDTO;
import com.wladska.masters.experiment2.dtos.EmployeeDTO;
import com.wladska.masters.experiment2.model.Department;
import com.wladska.masters.experiment2.model.Employee;
import com.wladska.masters.experiment2.repository.DepartmentRepository;
import com.wladska.masters.experiment2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return employees.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result) {
        if (result.hasErrors()) {
            // Collect and return all validation errors
            List<String> errorList = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorList);
        }

        Employee employee = convertToEmployee(employeeDTO); // Assume this method converts DTO to Entity
        Employee savedEmployee = employeeService.save(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();
        return ResponseEntity.created(location).body(savedEmployee);
    }

    private Employee convertToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        Department department = departmentRepository.findById(employeeDTO.getDepartment().getId()).orElse(null);
        employee.setDepartment(department);
        return employee;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(employee -> ResponseEntity.ok(convertToDTO(employee))) // Convert to DTO before sending the response
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setName(employeeDetails.getName());
                    existingEmployee.setPosition(employeeDetails.getPosition());
                    existingEmployee.setSalary(employeeDetails.getSalary());
                    existingEmployee.setDepartment(employeeDetails.getDepartment());
                    Employee updatedEmployee = employeeService.save(existingEmployee);
                    return ResponseEntity.ok(updatedEmployee);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(employee -> {
                    String name = employee.getName();
                    employeeService.delete(employee);
                    Map<String, String> response = new HashMap<>();
                    response.put("name", name);
                    response.put("message", "Employee deleted successfully");
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        DepartmentDTO departmentDTO = new DepartmentDTO(employee.getDepartment().getId(), employee.getDepartment().getName());
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getPosition(),
                employee.getSalary(),
                departmentDTO
        );
    }
}