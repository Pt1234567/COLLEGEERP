package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.Dto.AssignDto;
import com.project.COLLEGEERP.Dto.ClassDto;
import com.project.COLLEGEERP.Dto.StudentDTO;
import com.project.COLLEGEERP.Service.StudentService;
import com.project.COLLEGEERP.Service.TeacherService;
import com.project.COLLEGEERP.Service.AdminService;
import com.project.COLLEGEERP.config.SecurityConfig;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.helper.Gender;
import com.project.COLLEGEERP.helper.Role;
import com.project.COLLEGEERP.repository.AssignRepository;
import com.project.COLLEGEERP.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    @Autowired
    private AssignRepository assignRepository;



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(
            @RequestBody StudentDTO studentDTO
            ) throws ParseException {
             Class classEntity= adminService.getClassByClassId(studentDTO.getClassId());
             if(classEntity==null){
                 return new ResponseEntity<>("Class Not found", HttpStatus.NOT_FOUND);
             }

             //create username and password
             String username=studentDTO.getStudentName().split(" ")[0].toLowerCase()+'_'+studentDTO.getStudentId().substring(studentDTO.getStudentId().length()-3);
             String password=studentDTO.getStudentName().split(" ")[0].toLowerCase()+'_'+studentDTO.getDob().split("-")[0];

             //create a user for student
              User user=new User();
              user.setUserName(username);
              user.setPassword(securityConfig.passwordEncoder().encode(password));
              user.setRole(Role.ROLE_STUDENT);
              User savedUser= adminService.saveUser(user);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // Define format
                Date dobDate = sdf.parse(studentDTO.getDob()); // Parse string to Date

              //creating stduent
              Student student=new Student();
              student.setUser(savedUser);
              student.setStudentId(studentDTO.getStudentId());
              student.setStudentName(studentDTO.getStudentName());
              student.setDob(dobDate);
              student.setStudentGender(studentDTO.getGender().equals("MALE") ? Gender.MALE : Gender.FEMALE);
              student.setClassId(classEntity);

              studentService.saveStudent(student);
              return new ResponseEntity<>("Student Saved",HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addTeacher")
    public ResponseEntity<String> addTeacher(
            @RequestBody TeacherDto teacherDto
    ) throws ParseException {
        //create username and password
        String username= teacherDto.getName().split(" ")[0].toLowerCase()+'_'+teacherDto.getTeacherId();
        String password= teacherDto.getName().split(" ")[0].toLowerCase()+'_'+teacherDto.getDob().toString().split("-")[0];

        User user=new User();
        user.setRole(Role.ROLE_TEACHER);
        user.setUserName(username);
        user.setPassword(securityConfig.passwordEncoder().encode(password));
        User savedUser= adminService.saveUser(user);

        //create teacher
        Teacher t1=new Teacher();
        t1.setUser(savedUser);
        t1.setTeacherId(teacherDto.getTeacherId());
        t1.setName(teacherDto.getName());
        t1.setGender(teacherDto.getGender().equals("MALE") ? Gender.MALE : Gender.FEMALE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // Define format
        Date dobDate = sdf.parse(teacherDto.getDob()); // Parse string to Date
        t1.setDob(dobDate);
        t1.setEmail(teacherDto.getEmail());
        Department department=adminService.getDepartmentById(teacherDto.getDeptId());
        t1.setDepartment(department);
        teacherService.saveTeacher(t1);

        return new ResponseEntity<>("Teacher added",HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addClass")
    public ResponseEntity<String> addClass(
            @RequestBody ClassDto classDto
    ){
       Department department= adminService.getDepartmentById(classDto.getDeptId());

       Class classEntity=new Class();
       classEntity.setId(classDto.getClassId());
       classEntity.setDepartment(department);
       classEntity.setSem(classDto.getSem());
       adminService.saveClassEntity(classEntity);

       return new ResponseEntity<>("Class added",HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign")
    public ResponseEntity<Assign> assignToCourseAndRespectiveTeacher(
            @RequestBody AssignDto assignDto
            ){
         Class classEntity= adminService.getClassByClassId(assignDto.getClassId());
         Course course= adminService.getCourseById(assignDto.getCourseId());
         Teacher teacher=teacherService.getTeacherById(assignDto.getTeacherId());

         Assign assign=assignRepository.findByClassId_IdAndCourse_CourseIdAndTeacher_TeacherId(classEntity.getId(), course.getCourseId(),teacher.getTeacherId());
         if(assign==null)assign=new Assign();
        // assign teacher to course and class
        assign.setCourse(course);
        assign.setTeacher(teacher);
        assign.setClassId(classEntity);
        Assign assigned=adminService.saveAssign(assign);

        return new ResponseEntity<>(assigned,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")// Use 'ADMIN' (without 'ROLE_') since Spring Security adds the prefix internally
    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(
            @RequestBody CourseDto courseDto
    ){
         Department department=adminService.getDepartmentById(courseDto.getDeptId());
         Course course=new Course();
         course.setCourseId(courseDto.getCourseId());
         course.setCourseName(courseDto.getCourseName());
         course.setDepartment(department);

         Course savedCourse=adminService.saveCourse(course);
         return new ResponseEntity<>(savedCourse,HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addDepartment")
    public ResponseEntity<Department> addDepartment(
            @RequestBody Department department
    ){

            Department savedDepartment=adminService.saveDepartment(department);

            return new ResponseEntity<>(savedDepartment,HttpStatus.CREATED);
    }

}
