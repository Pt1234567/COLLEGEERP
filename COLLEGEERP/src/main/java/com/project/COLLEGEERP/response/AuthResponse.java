package com.project.COLLEGEERP.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String userName;
    private String jwt;
    private String message;
    private boolean isPasswordSet;
    private String role;
    private String session;
}
