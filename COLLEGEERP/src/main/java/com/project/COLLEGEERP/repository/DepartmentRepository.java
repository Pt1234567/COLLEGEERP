package com.project.COLLEGEERP.repository;

import com.project.COLLEGEERP.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDeptName(String DeptName);
}
