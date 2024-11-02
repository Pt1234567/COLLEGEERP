package com.project.COLLEGEERP.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.COLLEGEERP.helper.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    private String studentId;

    private String studentName;

    private String studentEmail;

    @Enumerated(EnumType.STRING)
    private Gender studentGender;

    private Date dob;


    @OneToMany(mappedBy = "student")
    private List<Marks> marksList=new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances=new ArrayList<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private User user;


    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classId;
}
