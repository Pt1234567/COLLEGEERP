package com.project.COLLEGEERP.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "dept_id")
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendanceList=new ArrayList<>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Marks> marksList=new ArrayList<>();
}
