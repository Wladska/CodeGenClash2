package com.wladska.masters.experiment2.init;

import com.wladska.masters.experiment2.entities.Department;
import com.wladska.masters.experiment2.entities.Employee;
import com.wladska.masters.experiment2.repositories.DepartmentRepository;
import com.wladska.masters.experiment2.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class Initializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public Initializer(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create departments
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Department department = new Department();
            department.setName("Department " + i);
            departmentRepository.save(department);

            // Create employees
            IntStream.rangeClosed(1, 4).forEach(j -> {
                Employee employee = new Employee();
                employee.setName("Employee " + j + " of Department " + i);
                employee.setEmail("employee" + j + "ofDepartment" + i + "@example.com");
                employee.setDepartment(department);
                employeeRepository.save(employee);
            });
        });
    }
}