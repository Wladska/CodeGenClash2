package com.wladska.masters.experiment2.repositories;

import com.wladska.masters.experiment2.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByEmployeeId(Long employeeId);

    Optional<Department> findById(Long id);

    Optional<Department> findByName(String name);

    List<Department> findAll();
}