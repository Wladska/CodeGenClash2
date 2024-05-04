package com.wladska.masters.experiment2.repository;

import com.wladska.masters.experiment2.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}