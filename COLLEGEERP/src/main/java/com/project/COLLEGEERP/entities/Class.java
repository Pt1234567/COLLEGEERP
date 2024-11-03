package com.project.COLLEGEERP.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Class {

    @Id
    private String id;

    private int sem;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToMany(mappedBy = "classId")
    private Set<Student> studentList=new HashSet<>();

}
