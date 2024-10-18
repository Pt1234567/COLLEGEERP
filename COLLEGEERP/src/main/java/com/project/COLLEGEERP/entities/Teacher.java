package com.project.COLLEGEERP.entities;

import com.project.COLLEGEERP.helper.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Teacher
{
    @Id
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses=new ArrayList<>();
}
