package com.project.COLLEGEERP.Service.Impl;

import com.project.COLLEGEERP.Service.TeacherService;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.repository.AttendanceRepository;
import com.project.COLLEGEERP.repository.CourseRepository;
import com.project.COLLEGEERP.repository.StudentRepository;
import com.project.COLLEGEERP.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;





    @Override
    public void markAttendance(String studentId, String courseId,boolean isPresent) {
        Course course=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("No course found"));
        Student student=studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("No Student found"));
        Attendance attendance=attendanceRepository.findByStudent_StudentIdAndCourse_CourseId(studentId,courseId);
        if(attendance==null){
            attendance=new Attendance(0,0);
            attendance.setStudent(student);
            attendance.setCourse(course);
        }
        int presentCount=attendance.getTotalAttended();
        int totalClasses= attendance.getTotalClasses();
        if (isPresent)presentCount++;
        totalClasses++;
        attendance.setTotalAttended(presentCount);
        attendance.setTotalClasses(totalClasses);
        attendance.setStatus(isPresent);

        attendanceRepository.save(attendance);
    }

    @Override
    public void setMarks(String studentId, String courseId) {

    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(()->new RuntimeException("Teacher Not found"));
    }


}
