package com.wladska.masters.experiment2.repositories;

import com.wladska.masters.experiment2.entities.Department;
import com.wladska.masters.experiment2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByDepartmentName(String departmentName);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();
}