package com.cosmind.schooladmin.dto;

import com.cosmind.schooladmin.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentClassCourseResponseDTO {
    private Long id;
    private String courseName;
    private String description;
    private CourseTeacher teacher;

    public static StudentClassCourseResponseDTO toStudentClassCourseResponseDTO(Course course) {
        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setId(course.getTeacher().getId());
        courseTeacher.setFirstName(course.getTeacher().getFirstName());
        courseTeacher.setLastName(course.getTeacher().getLastName());
        courseTeacher.setPhotoFileName(course.getTeacher().getPhotoFile());
        return StudentClassCourseResponseDTO.builder()
                .id(course.getId())
                .courseName(course.getName())
                .teacher(courseTeacher)
                .build();
    }

    @Data
    @NoArgsConstructor
    private static class CourseTeacher {
        private Long id;
        private String firstName;
        private String lastName;
        private String photoFileName;
    }
}
