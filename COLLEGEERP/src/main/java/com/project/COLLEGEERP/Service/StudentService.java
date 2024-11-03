package com.project.COLLEGEERP.Service;

import com.project.COLLEGEERP.entities.Student;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudentById(String studentId);
}
