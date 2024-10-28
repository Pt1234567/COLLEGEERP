package com.project.COLLEGEERP.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime localDateTime=LocalDateTime.now();

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private int totalClasses;
    
    private int totalAttended;

    public Attendance(int totalClasses, int totalAttended) {
        this.totalClasses = totalClasses;
        this.totalAttended = totalAttended;
    }



}
