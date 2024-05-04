package com.wladska.masters.experiment2.repository;

import com.wladska.masters.experiment2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  EmployeeRepository extends JpaRepository<Employee, Long> {
    // Find employees by department name
    List<Employee> findByDepartmentName(String departmentName);

    // Find employees by position
    List<Employee> findByPosition(String position);

    // Advanced search with dynamic query for multiple fields (e.g., department and position)
    @Query("SELECT e FROM Employee e WHERE (:departmentName IS NULL OR e.department.name = :departmentName) AND (:position IS NULL OR e.position = :position)")
    List<Employee> findByDepartmentAndPosition(
            @Param("departmentName") String departmentName,
            @Param("position") String position
    );
}
