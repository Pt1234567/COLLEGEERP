package com.project.COLLEGEERP.Service;

import com.project.COLLEGEERP.entities.Attendance;
import com.project.COLLEGEERP.entities.Course;
import com.project.COLLEGEERP.entities.Student;

public interface AttendanceService {

    Attendance getByStudentIdAndCourseId(String studentId, String courseId);

}
