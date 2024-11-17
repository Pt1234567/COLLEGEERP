package com.project.COLLEGEERP.repository;

import com.project.COLLEGEERP.entities.Assign;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignRepository extends JpaRepository<Assign,Long> {
    List<Course> findCourseByTeacher_TeacherId(String teacherId);
    List<Class> findClassIdByTeacher_TeacherId(String teacherId);

    List<Assign> findAssignByClassId_Id(String classId);
}
