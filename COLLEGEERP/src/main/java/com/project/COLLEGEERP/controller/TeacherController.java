package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.Service.TeacherService;
import com.project.COLLEGEERP.entities.Student;
import com.project.COLLEGEERP.entities.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/attendance")
    public ResponseEntity<String> markAttendance(){
        return ResponseEntity.ok("Go to Attendance");
    }

    @GetMapping("/attendance/{courseId}")
    public ResponseEntity<List<StudentDto>> getStudentList(@RequestBody String courseId){
         List<StudentDto> studentDtoList=teacherService.getStudentByCourse(courseId);
         return ResponseEntity.ok(studentDtoList);
    }

    @PostMapping("/attendance/{courseId}")
    public ResponseEntity<String> markAttendanceOfStudent(@RequestBody Map<String,Object> request,@PathVariable String courseId){
        String studentId=(request.get("studentId").toString());
        boolean status=Boolean.parseBoolean(request.get("status").toString());
        teacherService.markAttendance(studentId,courseId,status);
        return ResponseEntity.ok("Attendacne Marked");
    }

}
