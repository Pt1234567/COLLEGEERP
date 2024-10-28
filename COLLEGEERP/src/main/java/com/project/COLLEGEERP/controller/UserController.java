package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.Service.UserService;
import com.project.COLLEGEERP.entities.Course;
import com.project.COLLEGEERP.entities.Department;
import com.project.COLLEGEERP.entities.User;
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

    @PostMapping("/addUser")
     public ResponseEntity<User> adminAddUser(@RequestBody User user){
         User newUser=userService.addUser(user);
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
