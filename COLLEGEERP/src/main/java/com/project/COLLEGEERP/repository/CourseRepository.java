package com.project.COLLEGEERP.repository;

import com.project.COLLEGEERP.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,String> {
}
