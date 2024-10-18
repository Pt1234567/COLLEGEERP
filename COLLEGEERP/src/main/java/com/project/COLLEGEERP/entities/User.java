package com.project.COLLEGEERP.entities;

import com.project.COLLEGEERP.helper.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Role role;

    //use teacher email for login
    // use student roll no for login
    private String userId;

    private String password;

    private boolean isPasswordSet=false;

}
