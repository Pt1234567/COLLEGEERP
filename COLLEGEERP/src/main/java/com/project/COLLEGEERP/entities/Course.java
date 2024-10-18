package com.project.COLLEGEERP.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    private String courseId;

    private String courseName;

    private String courseShortName;

    @ManyToMany(mappedBy = "courses")
    private List<Student> studentList=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendanceList=new ArrayList<>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Marks> marksList=new ArrayList<>();
}
