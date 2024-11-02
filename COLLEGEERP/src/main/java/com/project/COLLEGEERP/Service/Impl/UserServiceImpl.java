package com.project.COLLEGEERP.Service.Impl;

import com.project.COLLEGEERP.Service.UserService;
import com.project.COLLEGEERP.config.JwtProvider;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.entities.Class;
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

    @Autowired
    private ClassRepository classRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
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

    @Override
    public Class getClassByClassId(String classId) {
        return classRepository.findById(classId).orElseThrow(()->new RuntimeException("Class Not found"));
    }

    @Override
    public Department getDepartmentByDeptName(String deptName) {
        return departmentRepository.findByDeptName(deptName);
    }


}
