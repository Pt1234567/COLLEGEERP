package com.project.COLLEGEERP.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private String courseId;
    private String courseName;
    private String deptId;

}
