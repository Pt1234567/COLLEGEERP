package com.project.COLLEGEERP.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignDto {
    private String classId;
    private String courseId;
    private String teacherId;
}
