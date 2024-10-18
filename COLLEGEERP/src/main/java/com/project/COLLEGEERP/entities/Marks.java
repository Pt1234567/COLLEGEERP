package com.project.COLLEGEERP.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int midMark;
    private int endMark;
    private int testMark;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
