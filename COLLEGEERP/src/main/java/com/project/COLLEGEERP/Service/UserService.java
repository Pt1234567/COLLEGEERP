package com.project.COLLEGEERP.Service;

import com.project.COLLEGEERP.entities.Course;
import com.project.COLLEGEERP.entities.Department;
import com.project.COLLEGEERP.entities.User;

public interface UserService {
    User addUser(User user);
    Course addCourse(Course course);
    Department addDepartment(Department department);

    User findUserByJwt(String token);

    User findUserByUserId(String userId);
}
