package com.project.COLLEGEERP.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Department {

    @Id
    private String id;

    @Column(unique = true)
    private String deptName;


    @OneToMany(mappedBy = "department")
    private List<Teacher> teacherList=new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<Course> courses=new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<Class> classList = new ArrayList<>();
}
