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
public class Teacher
{
    @Id
    private String teacherId;

    private String name;

    @Column(unique = true)
    private String email;

    private Gender gender;

    private Date dob;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses=new ArrayList<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private User user;
}
