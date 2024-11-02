package com.project.COLLEGEERP.Service;

import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.entities.Course;
import com.project.COLLEGEERP.entities.Department;
import com.project.COLLEGEERP.entities.User;

public interface UserService {
    User saveUser(User user);
    Course saveCourse(Course course);
    Department saveDepartment(Department department);

    User findUserByJwt(String token);

    User findUserByUserId(String userId);

    Class getClassByClassId(String classId);

    Department getDepartmentByDeptName(String deptName);
}
