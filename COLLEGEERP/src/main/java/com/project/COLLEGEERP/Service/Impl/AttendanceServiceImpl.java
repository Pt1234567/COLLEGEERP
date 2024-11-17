package com.project.COLLEGEERP.Service.Impl;

import com.project.COLLEGEERP.Service.AttendanceService;
import com.project.COLLEGEERP.entities.Attendance;
import com.project.COLLEGEERP.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Override
    public Attendance getByStudentIdAndCourseId(String studentId, String courseId) {
          return attendanceRepository.findByStudent_StudentIdAndCourse_CourseId(studentId,courseId);
    }
}
