package com.project.COLLEGEERP.Service.Impl;

import com.project.COLLEGEERP.Service.AssignService;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.entities.Course;
import com.project.COLLEGEERP.repository.AssignRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AssignServiceImpl implements AssignService {

    @Autowired
    private AssignRepository assignRepository;

    @Override
    public List<Course> getAllCourseByTeacherId(String teacherId) {
        return assignRepository.findCourseByTeacher_TeacherId(teacherId);
    }

    @Override
    public List<Class> getAllClassByTeacherId(String teacherId) {
        return assignRepository.findClassIdByTeacher_TeacherId(teacherId);
    }
}
