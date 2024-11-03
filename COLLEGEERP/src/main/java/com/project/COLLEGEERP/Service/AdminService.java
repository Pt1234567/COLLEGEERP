package com.project.COLLEGEERP.Service;

import com.project.COLLEGEERP.entities.*;
import com.project.COLLEGEERP.entities.Class;

public interface AdminService {
    User saveUser(User user);
    Course saveCourse(Course course);
    Department saveDepartment(Department department);

    User findUserByJwt(String token);



    User getUserByUserName(String userName);

    Class getClassByClassId(String classId);

    Department getDepartmentById(String deptId);

    Class saveClassEntity(Class classEntity);

    Course getCourseById(String courseId);


    Assign saveAssign(Assign assign);
}
