package com.project.COLLEGEERP.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Class {

    @Id
    private String id;

    private int sem;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToMany(mappedBy = "classId")
    private List<Student> studentList=new ArrayList<>();

}
