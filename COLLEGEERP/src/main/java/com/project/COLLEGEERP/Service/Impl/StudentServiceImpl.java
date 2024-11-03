package com.project.COLLEGEERP.Service.Impl;

import com.project.COLLEGEERP.Service.StudentService;
import com.project.COLLEGEERP.entities.Student;
import com.project.COLLEGEERP.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student Not found"));
    }
}
