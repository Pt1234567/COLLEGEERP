package com.project.COLLEGEERP.entities;

import com.project.COLLEGEERP.helper.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    private String teacherId;

    private String name;


    private String email;

    private String gender;

    private String dob;

    private String deptId;

}
