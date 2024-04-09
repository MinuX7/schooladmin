package com.cosmind.schooladmin.dto;

import com.cosmind.schooladmin.model.StudentClass;
import com.cosmind.schooladmin.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentClassFullDetailsDTO {
    private Long id;
    private Integer grade;
    private Character label;
    private List<CourseDTO1> courses;

    public static StudentClassFullDetailsDTO toStudentClassDetailsDTO(StudentClass studentClass) {
        List<CourseDTO1> coursesDTO = studentClass.getCourses().stream().map(course -> {
            Teacher teacher = course.getTeacher();
            TeacherDTO teacherDTO = TeacherDTO.builder()
                    .firstName(teacher.getFirstName())
                    .lastName(teacher.getLastName())
                    .photoFileName(teacher.getPhotoFile())
                    .build();
            return CourseDTO1.builder()
                    .courseName(course.getName())
                    .description(course.getDescription())
                    .teacher(teacherDTO)
                    .build();
        }).collect(Collectors.toList());
        StudentClassFullDetailsDTO studentClassDto = StudentClassFullDetailsDTO.builder()
                .id(studentClass.getId())
                .grade(studentClass.getGrade())
                .label(studentClass.getLabel())
                .courses(coursesDTO)
                .build();
        return studentClassDto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class CourseDTO1 {
        private String courseName;
        private String description;
        private TeacherDTO teacher;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class TeacherDTO1 {
        private Long id;
        private String firstName;
        private String lastName;
        private String photoFileName;
    }

}
