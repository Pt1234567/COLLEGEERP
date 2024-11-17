package com.project.COLLEGEERP.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    private String courseId;
    private String courseName;
    private int totalClasses;
    private int totalAttended;
    private int percentage;

}
