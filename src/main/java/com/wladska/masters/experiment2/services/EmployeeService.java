package com.wladska.masters.experiment2.services;

import com.wladska.masters.experiment2.dtos.DepartmentDTO;
import com.wladska.masters.experiment2.dtos.EmployeeDTO;
import com.wladska.masters.experiment2.entities.Department;
import com.wladska.masters.experiment2.entities.Employee;
import com.wladska.masters.experiment2.repositories.DepartmentRepository;
import com.wladska.masters.experiment2.repositories.EmployeeRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment().getId()); // get the id of the department
        return dto;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
            .map(this::toEmployeeDTO)
            .collect(Collectors.toList());
    }

    // Implement other CRUD operations...

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return toEmployeeDTO(employee);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(departmentRepository.findById(employeeDTO.getDepartment())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + employeeDTO.getDepartment())));
        Employee savedEmployee = employeeRepository.save(employee);
        return toEmployeeDTO(savedEmployee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(departmentRepository.findById(employeeDTO.getDepartment())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + employeeDTO.getDepartment())));
        Employee updatedEmployee = employeeRepository.save(employee);
        return toEmployeeDTO(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    public DepartmentDTO getDepartmentByEmployeeId(Long id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return toDepartmentDTO(employee.getDepartment());
    }

    private DepartmentDTO toDepartmentDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setEmployeeIds(department.getEmployees().stream()
            .map(Employee::getId)
            .collect(Collectors.toList()));
        return dto;
    }
}