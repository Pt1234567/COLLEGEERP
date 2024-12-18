package com.project.COLLEGEERP.Service.Impl;

import com.project.COLLEGEERP.Service.AssignService;
import com.project.COLLEGEERP.entities.Assign;
import com.project.COLLEGEERP.entities.Class;
import com.project.COLLEGEERP.entities.Course;
import com.project.COLLEGEERP.repository.AssignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignServiceImpl implements AssignService {

    @Autowired
    private AssignRepository assignRepository;

    @Override
    public List<String> getAllCourseByTeacherId(String teacherId) {
        List<Assign> assigns= assignRepository.findByTeacher_TeacherId(teacherId);
        List<String> courses=new ArrayList<>();
        for(Assign assign:assigns){
            courses.add(assign.getCourse().getCourseId());
        }
        return courses;
    }

    @Override
    public List<String> getAllClassByTeacherId(String teacherId) {
        List<Assign> assigns= assignRepository.findByTeacher_TeacherId(teacherId);
        List<String> classList=new ArrayList<>();
        for(Assign assign:assigns){
            classList.add(assign.getClassId().getId());
        }
        return classList;
    }

    @Override
    public List<Assign> getAllAssignedByClassId(String classId) {
       return assignRepository.findByClassId_Id(classId);
    }
}
