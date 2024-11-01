package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.Service.UserService;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.helper.Role;
import com.project.COLLEGEERP.repository.StudentRepository;
import com.project.COLLEGEERP.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
     private UserService userService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping("/addUser")
     public ResponseEntity<User> adminAddUser(@RequestBody User user){
         User newUser=userService.addUser(user);
         if(newUser.getRole()== Role.STUDENT){
             Student student = new Student();
             student.setUser(newUser);
             student.setStudentId(user.getUserId());
             studentRepository.save(student);
         }else if(newUser.getRole()==Role.TEACHER){
             Teacher teacher = new Teacher();
             teacher.setUser(newUser);
             teacher.setId(user.getUserId());
             teacherRepository.save(teacher);
         }
         return ResponseEntity.ok(newUser);
     }

     @PostMapping("/addCourse")
     public ResponseEntity<Course> adminAddCourse(@RequestBody Course course){
         Course newCourse=userService.addCourse(course);
         return ResponseEntity.ok(newCourse);
     }

     @PostMapping("/addDepartment")
     public ResponseEntity<Department> adminAddDepartment(@RequestBody Department department){

         Department newDepartment=new Department();
         newDepartment.setDeptName(department.getDeptName());
         return ResponseEntity.ok(userService.addDepartment(newDepartment));
     }

}
