package com.project.COLLEGEERP.Service;

import com.project.COLLEGEERP.entities.Assign;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.entities.Course;
import com.project.COLLEGEERP.entities.Teacher;

import java.util.List;

public interface AssignService {
    List<String> getAllCourseByTeacherId(String teacherId);
    List<String> getAllClassByTeacherId(String teacherId);

    List<Assign>  getAllAssignedByClassId(String classId);
}
