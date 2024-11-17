package com.project.COLLEGEERP.entities;

import com.project.COLLEGEERP.helper.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String password;

    private Role role;

    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Teacher teacher;

}
