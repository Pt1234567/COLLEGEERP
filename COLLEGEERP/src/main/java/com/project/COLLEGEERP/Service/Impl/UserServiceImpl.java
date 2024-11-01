package com.project.COLLEGEERP.Service.Impl;

import com.project.COLLEGEERP.Service.UserService;
import com.project.COLLEGEERP.config.JwtProvider;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.helper.Role;
import com.project.COLLEGEERP.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

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

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User addUser(User user) {
        user.setPasswordSet(false);
        User savedUser=userRepository.save(user);
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

    @Override
    public User findUserByJwt(String token) {
         String userId=jwtProvider.generateUserIdFromToken(token);
         User user=userRepository.findByUserId(userId);
         return user;
    }

    @Override
    public User findUserByUserId(String userId) {
        User user=userRepository.findByUserId(userId);
        return  user;
    }


}
