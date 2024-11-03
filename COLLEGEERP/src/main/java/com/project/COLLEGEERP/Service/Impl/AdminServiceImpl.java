package com.project.COLLEGEERP.Service.Impl;

import com.project.COLLEGEERP.Service.AdminService;
import com.project.COLLEGEERP.config.JwtProvider;
import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

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

    @Autowired
    private AssignRepository assignRepository;


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
        String userName=jwtProvider.generateUserNameFromToken(token);
        User user=userRepository.findByUserName(userName);
        return user;
    }



    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Class getClassByClassId(String classId) {
        return classRepository.findById(classId).orElseThrow(()->new RuntimeException("Class Not found"));
    }

    @Override
    public Department getDepartmentById(String deptId) {
        return departmentRepository.findById(deptId).orElseThrow(()->new RuntimeException("No dept found"));
    }

    @Override
    public Class saveClassEntity(Class classEntity) {
        return classRepository.save(classEntity);
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Course Not found"));
    }

    @Override
    public Assign saveAssign(Assign assign) {
        return assignRepository.save(assign);
    }


}
