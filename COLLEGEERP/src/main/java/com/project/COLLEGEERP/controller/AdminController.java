package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.Service.StudentService;
import com.project.COLLEGEERP.Service.UserService;
import com.project.COLLEGEERP.config.SecurityConfig;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.helper.Gender;
import com.project.COLLEGEERP.helper.Role;
import com.project.COLLEGEERP.repository.ClassRepository;
import com.project.COLLEGEERP.repository.StudentRepository;
import com.project.COLLEGEERP.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
     private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SecurityConfig securityConfig;



    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(
            @RequestParam  String classId,
            @RequestParam  String studentName,
            @RequestParam  String studentId,
            @RequestParam  String dob,
            @RequestParam  String gender
    ){
             Class classEntity= userService.getClassByClassId(classId);
             if(classEntity==null){
                 return new ResponseEntity<>("Class Not found", HttpStatus.NOT_FOUND);
             }

             //create username and password
             String username=studentName.split(" ")[0].toLowerCase()+'_'+studentId.substring(studentId.length()-3);
             String password=studentName.split(" ")[0].toLowerCase()+'_'+dob.split("-")[0];

             //create a user for student
              User user=new User();
              user.setUserName(username);
              user.setPassword(securityConfig.passwordEncoder().encode(password));
              user.setRole(Role.STUDENT);
              User savedUser=userService.saveUser(user);

              Date dateOfBirth=java.sql.Date.valueOf(dob);//convert string to date
              Gender g=((gender.equals("Male"))?Gender.MALE:Gender.FEMALE);

              //creating stduent
              Student student=new Student();
              student.setUser(savedUser);
              student.setStudentId(studentId);
              student.setStudentName(studentName);
              student.setDob(dateOfBirth);
              student.setStudentGender(g);
              student.setClassId(classEntity);

              studentService.saveStudent(student);
              return new ResponseEntity<>("Student Saved",HttpStatus.CREATED);
    }

    public ResponseEntity<String> addTeacher(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String dob,
            @RequestParam String gender,
            @RequestParam String deptName
    ){

        Department department=userService.getDepartmentByDeptName(deptName);

        //create username and password
        String username=name.split(" ")[0].toLowerCase()+'_'+id;
        String password=name.split(" ")[0].toLowerCase()+'_'+dob.split("-")[0];

        User user=new User();
        user.setRole(Role.TEACHER);
        user.setUserName(username);
        user.setPassword(securityConfig.passwordEncoder().encode(password));
        User savedUser=userService.saveUser(user);

        Date dateOfBirth=java.sql.Date.valueOf(dob);//convert string to date
        Gender g=((gender.equals("Male"))?Gender.MALE:Gender.FEMALE);

        //create teacher
        Teacher teacher=new Teacher();
        teacher.setTeacherId(id);
        teacher.setUser(savedUser);
        teacher.setGender(g);
        teacher.setDob(dateOfBirth);
        teacher.setDepartment(department);
        teacher.setName(name);
        teacherRepository.save(teacher);
        return new ResponseEntity<>("Teacher added",HttpStatus.CREATED);
    }

}
