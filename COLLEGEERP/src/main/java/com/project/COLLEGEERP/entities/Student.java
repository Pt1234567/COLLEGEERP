package com.project.COLLEGEERP.entities;

import com.project.COLLEGEERP.helper.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    private String studentId;

    private String studentName;

    private String studentEmail;

    private int sem;

    @Enumerated(EnumType.STRING)
    private Gender studentGender;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses=new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Marks> marksList=new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances=new ArrayList<>();
}
