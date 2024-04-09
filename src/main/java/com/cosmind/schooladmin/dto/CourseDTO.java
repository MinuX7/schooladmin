package com.cosmind.schooladmin.dto;

import com.cosmind.schooladmin.model.Course;
import com.cosmind.schooladmin.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private Long id;
    private String courseName;
    private String description;
    CourseTeacherDTO teacher;

    @Data
    @NoArgsConstructor
    private static class CourseTeacherDTO {
        private Long id;
        private String firstName;
        private String lastName;
    }
    public static CourseDTO toCourseDTO(Course course) {
        CourseTeacherDTO teacherDTO = new CourseTeacherDTO();
        teacherDTO.setId(course.getTeacher().getId());
        teacherDTO.setFirstName(course.getTeacher().getFirstName());
        teacherDTO.setLastName(course.getTeacher().getLastName());
        return CourseDTO.builder()
                .id(course.getId())
                .courseName(course.getName())
                .description(course.getDescription())
                .teacher(teacherDTO)
                .build();
    }

    public static Course toCourseModel(CourseDTO courseDTO) {
        Teacher teacher = new Teacher();
        teacher.setId(courseDTO.getTeacher().getId());
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getCourseName());
        course.setDescription(courseDTO.getDescription());
        course.setTeacher(teacher);
        return course;
    }

}
