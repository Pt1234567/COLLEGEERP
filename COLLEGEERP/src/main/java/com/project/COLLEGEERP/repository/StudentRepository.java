package com.project.COLLEGEERP.repository;

import com.project.COLLEGEERP.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
}
