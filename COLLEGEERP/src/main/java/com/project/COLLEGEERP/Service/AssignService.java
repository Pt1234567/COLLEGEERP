package com.project.COLLEGEERP.Service;

import com.project.COLLEGEERP.entities.Assign;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.entities.Course;
import com.project.COLLEGEERP.entities.Teacher;

import java.util.List;

public interface AssignService {
    List<Course> getAllCourseByTeacherId(String teacherId);
    List<Class> getAllClassByTeacherId(String teacherId);

    List<Assign>  getAllAssignedByClassId(String classId);
}
