package com.project.COLLEGEERP.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private boolean isPasswordSet;
    private String session;
}
