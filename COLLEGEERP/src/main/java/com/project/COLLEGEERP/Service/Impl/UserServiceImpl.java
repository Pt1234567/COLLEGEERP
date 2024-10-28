package com.project.COLLEGEERP.Service.Impl;

import com.project.COLLEGEERP.Service.UserService;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.helper.Role;
import com.project.COLLEGEERP.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public User addUser(User user) {
        User user1=new User();
        user1.setRole(user.getRole());
        user1.setPassword("abc123");
        user1.setPasswordSet(false);
        user1.setUserId(user.getUserId());
        User savedUser=userRepository.save(user1);
        switch (user.getRole()) {
            case STUDENT -> {
                Student student = new Student();
                student.setUser(savedUser);
                student.setStudentId(user.getUserId());
                studentRepository.save(student);
            }
            case TEACHER -> {
                Teacher teacher = new Teacher();
                teacher.setUser(savedUser);
                teacher.setId(user.getUserId());
                teacherRepository.save(teacher);
            }
        }

        return savedUser;
    }

    @Override
    public Course addCourse(Course course) {
        Course course1=new Course();
        course1.setCourseName(course.getCourseName());
        course1.setCourseShortName(course.getCourseShortName());
        course1.setCourseId(course.getCourseId());
        return courseRepository.save(course1);
    }

    @Override
    public Department addDepartment(Department department) {
        Department department1=new Department();
        department1.setDeptName(department.getDeptName());
        return departmentRepository.save(department1);
    }
}
