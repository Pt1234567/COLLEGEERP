package com.project.COLLEGEERP.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Assign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignId;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
