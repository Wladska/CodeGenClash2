package com.wladska.masters.experiment2.init;

import com.wladska.masters.experiment2.model.Department;
import com.wladska.masters.experiment2.model.Employee;
import com.wladska.masters.experiment2.model.Role;
import com.wladska.masters.experiment2.model.User;
import com.wladska.masters.experiment2.repository.DepartmentRepository;
import com.wladska.masters.experiment2.repository.EmployeeRepository;
import com.wladska.masters.experiment2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (departmentRepository.count() == 0 && userRepository.count() == 0) {
            // Creating departments
            Department it = new Department(null, "Information Technology", null);
            Department hr = new Department(null, "Human Resources", null);
            departmentRepository.saveAll(Arrays.asList(it, hr));

            // Creating employees and linking them to departments
            Employee emp1 = new Employee(null, "John Doe", "Software Engineer", 90000, it);
            Employee emp2 = new Employee(null, "Jane Smith", "HR Manager", 85000, hr);
            it.setEmployees(Arrays.asList(emp1));
            hr.setEmployees(Arrays.asList(emp2));
            employeeRepository.saveAll(Arrays.asList(emp1, emp2));

            // Creating users
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@company.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(new HashSet<>(Collections.singletonList(Role.ADMIN)));

            User user = new User();
            user.setName("Regular User");
            user.setEmail("user@company.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(new HashSet<>(Collections.singletonList(Role.USER)));

            userRepository.saveAll(Arrays.asList(admin, user));
        }
    }
}
