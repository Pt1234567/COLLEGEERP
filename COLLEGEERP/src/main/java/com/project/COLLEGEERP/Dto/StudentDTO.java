package com.project.COLLEGEERP.Dto;

import com.project.COLLEGEERP.helper.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private String classId;
    private String studentName;
    private String studentId;
    private String dob;
    private String gender;

}
