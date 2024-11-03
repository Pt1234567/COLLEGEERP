package com.project.COLLEGEERP.response;

import lombok.Data;

@Data
public class AttendanceResponse {
    private String message;
    private String studentId;
    private boolean attendanceMarked;
}