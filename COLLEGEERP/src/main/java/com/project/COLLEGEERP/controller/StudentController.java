package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.Service.AdminService;
import com.project.COLLEGEERP.Service.AssignService;
import com.project.COLLEGEERP.Service.AttendanceService;
import com.project.COLLEGEERP.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private AssignService assignService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AttendanceService attendanceService;

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/getAttendance")
    public ResponseEntity<List<AttendanceDto>> attendance(@RequestHeader("Authorization")String jwt){

        User user=adminService.findUserByJwt(jwt);
        Student student=user.getStudent();
        List<Assign> assignList=assignService.getAllAssignedByClassId(student.getClassId().getId());

        List<AttendanceDto> attendanceDtos=new ArrayList<>();

        for(Assign ass:assignList){
            System.out.println(student.getStudentId()+" "+ass.getCourse().getCourseId());
            Attendance att=attendanceService.getByStudentIdAndCourseId(student.getStudentId(),ass.getCourse().getCourseId());
            if(att==null) continue;
            AttendanceDto attendanceDto=new AttendanceDto();
            attendanceDto.setCourseId(att.getCourse().getCourseId());
            attendanceDto.setCourseName(att.getCourse().getCourseName());
            attendanceDto.setTotalClasses(att.getTotalClasses());
            attendanceDto.setTotalAttended(att.getTotalAttended());
            attendanceDto.setPercentage((att.getTotalAttended()/att.getTotalClasses())*100);
            attendanceDtos.add(attendanceDto);
        }
     return ResponseEntity.ok(attendanceDtos);
    }

}
