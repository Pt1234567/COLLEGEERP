package com.project.COLLEGEERP.Service;

import com.project.COLLEGEERP.entities.Student;
import com.project.COLLEGEERP.entities.StudentDto;

import java.util.List;

public interface TeacherService {

     List<StudentDto> getStudentByCourse(String courseId);

     void markAttendance(String studentId,String courseId,boolean isPresent);

     void setMarks(String studentId,String courseId);

}
