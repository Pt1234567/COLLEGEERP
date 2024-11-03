package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.Service.StudentService;
import com.project.COLLEGEERP.Service.TeacherService;
import com.project.COLLEGEERP.Service.AdminService;
import com.project.COLLEGEERP.config.SecurityConfig;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.helper.Gender;
import com.project.COLLEGEERP.helper.Role;
import com.project.COLLEGEERP.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
     private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SecurityConfig securityConfig;



    @PostMapping("/addStudent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addStudent(
            @RequestParam  String classId,
            @RequestParam  String studentName,
            @RequestParam  String studentId,
            @RequestParam  String dob,
            @RequestParam  String gender
    ){
             Class classEntity= adminService.getClassByClassId(classId);
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
              User savedUser= adminService.saveUser(user);

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

    @PostMapping("/addTeacher")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addTeacher(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String dob,
            @RequestParam String gender,
            @RequestParam String deptId
    ){

        Department department= adminService.getDepartmentById(deptId);

        //create username and password
        String username=name.split(" ")[0].toLowerCase()+'_'+id;
        String password=name.split(" ")[0].toLowerCase()+'_'+dob.split("-")[0];

        User user=new User();
        user.setRole(Role.TEACHER);
        user.setUserName(username);
        user.setPassword(securityConfig.passwordEncoder().encode(password));
        User savedUser= adminService.saveUser(user);

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
        teacherService.saveTeacher(teacher);
        return new ResponseEntity<>("Teacher added",HttpStatus.CREATED);
    }

    @PostMapping("/addClass")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addClass(
            @RequestParam String classId,
            @RequestParam String sem,
            @RequestParam String deptId
    ){
       Department department= adminService.getDepartmentById(deptId);

       Class classEntity=new Class();
       classEntity.setId(classId);
       classEntity.setDepartment(department);
       classEntity.setSem(Integer.parseInt(sem));
       adminService.saveClassEntity(classEntity);

       return new ResponseEntity<>("Class added",HttpStatus.CREATED);
    }

    @PostMapping("/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Assign> assignToCourseAndRespectiveTeacher(
            @RequestParam String classId,
            @RequestParam String courseId,
            @RequestParam String teacherId
    ){
         Class classEntity= adminService.getClassByClassId(classId);
         Course course= adminService.getCourseById(courseId);
         Teacher teacher=teacherService.getTeacherById(teacherId);

         Assign assign=new Assign();
        // assign teacher to course and class
        assign.setCourse(course);
        assign.setTeacher(teacher);
        assign.setClassId(classEntity);
        Assign assigned=adminService.saveAssign(assign);

        return new ResponseEntity<>(assigned,HttpStatus.CREATED);
    }

    @PostMapping("/addCourse")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Course> addCourse(
            @RequestParam String courseId,
            @RequestParam String courseName,
            @RequestParam String deptId
    ){

         Department department=adminService.getDepartmentById(deptId);
         Course course=new Course();
         course.setCourseId(courseId);
         course.setCourseName(courseName);
         course.setDepartment(department);

         Course savedCourse=adminService.saveCourse(course);
         return new ResponseEntity<>(savedCourse,HttpStatus.CREATED);
    }

    @PostMapping("/addDepartment")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Department> addDepartment(
            @RequestParam String deptId,
            @RequestParam String deptName
    ){
            Department department=new Department();
            department.setDeptName(deptName);
            department.setId(deptId);

            Department savedDepartment=adminService.saveDepartment(department);

            return new ResponseEntity<>(savedDepartment,HttpStatus.CREATED);
    }

}
