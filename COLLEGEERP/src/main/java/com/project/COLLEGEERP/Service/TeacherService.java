package com.project.COLLEGEERP.Service;

import com.project.COLLEGEERP.entities.Student;
import com.project.COLLEGEERP.entities.StudentDto;
import com.project.COLLEGEERP.entities.Teacher;

import java.util.List;

public interface TeacherService {



     void markAttendance(String studentId,String courseId,boolean isPresent);

     void setMarks(String studentId,String courseId);

     Teacher saveTeacher(Teacher teacher);

     Teacher getTeacherById(String teacherId);

}
