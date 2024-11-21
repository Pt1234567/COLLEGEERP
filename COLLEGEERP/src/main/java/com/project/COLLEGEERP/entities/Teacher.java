package com.project.COLLEGEERP.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.COLLEGEERP.helper.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
public class Teacher
{
    @Id
    private String teacherId;

    private String name;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Temporal(TemporalType.DATE) // This annotation is used to map a Date type to SQL DATE
    @Column(name = "dob")
    private Date dob;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private User user;
}
