package com.project.COLLEGEERP.entities;

import com.project.COLLEGEERP.helper.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //use teacher email for login
    // use student roll no for login
    private String userId;

    private String password;

    private Role role;

    private boolean isPasswordSet;

    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Teacher teacher;

}
