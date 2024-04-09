package com.cosmind.schooladmin.dto;

import com.cosmind.schooladmin.model.School;
import com.cosmind.schooladmin.model.StudentClass;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentClassDto {

    private Long id;
    private Integer grade;
    private Character label;
    private School school;

    public static StudentClass toStudentClassModel(StudentClassDto studentClassDto, School school) {
        StudentClass studentClass = new StudentClass();
        studentClass.setGrade(studentClassDto.getGrade());
        studentClass.setLabel(studentClassDto.getLabel());
        studentClass.setSchool(school);
        return studentClass;
    }

    public static StudentClassDto toStudentClassDto(StudentClass studentClass) {
        return StudentClassDto.builder()
                .id(studentClass.getId())
                .grade(studentClass.getGrade())
                .label(studentClass.getLabel())
            .build();
    }

}
