package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.Service.AdminService;
import com.project.COLLEGEERP.Service.AssignService;
import com.project.COLLEGEERP.Service.StudentService;
import com.project.COLLEGEERP.Service.TeacherService;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.response.AttendanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AssignService assignService;

    @GetMapping("/select")
    public ResponseEntity<Map<String, List<?>>> getClassesAndCourses(@RequestHeader("Authorization")String jwt) {
        User user=adminService.findUserByJwt(jwt);
        Teacher teacher=user.getTeacher();
        String teacherId=teacher.getTeacherId();
        List<Class> classes = assignService.getAllClassByTeacherId(teacherId); // Fetches all classes
        List<Course> courses = assignService.getAllCourseByTeacherId(teacherId);// Fetches all courses

        Map<String, List<?>> response = new HashMap<>();
        response.put("classes", classes);
        response.put("courses", courses);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{classId}/{courseId}/attendance")
    public ResponseEntity<List<Student>> getStudentsForClassAndCourse(
            @PathVariable String classId,
            @PathVariable String courseId) {
        Class classEntity=adminService.getClassByClassId(classId);
        List<Student> students = classEntity.getStudentList();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/{classId}/{courseId}/attendance")
    public ResponseEntity<AttendanceResponse> markAttendance(
            @RequestBody  Map<String, String> attendanceData,
            @PathVariable String courseId) {
       String studentId=attendanceData.get("studentId");
       String status=attendanceData.get("status");

       if(status.equals("PRESENT")){
           teacherService.markAttendance(studentId,courseId,true);
       }else{
           teacherService.markAttendance(studentId,courseId,false);
       }
        AttendanceResponse response = new AttendanceResponse();
        response.setMessage("Attendance marked");
        response.setStudentId(studentId);
        response.setAttendanceMarked(status.equals("PRESENT"));
        return ResponseEntity.ok(response);
    }


}
