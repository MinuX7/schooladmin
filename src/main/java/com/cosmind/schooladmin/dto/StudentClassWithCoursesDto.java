package com.cosmind.schooladmin.dto;

import com.cosmind.schooladmin.model.Course;
import com.cosmind.schooladmin.model.School;
import com.cosmind.schooladmin.model.StudentClass;
import com.cosmind.schooladmin.model.Teacher;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StudentClassWithCoursesDto {
    private StudentClassDTO studentClass;
    private List<CourseDTO> courses;

    public static StudentClass toStudentClassMode(StudentClassWithCoursesDto studentClassWithCoursesDto, School school) {
        StudentClass studentClassModel = new StudentClass();
        studentClassModel.setGrade(studentClassWithCoursesDto.getStudentClass().getGrade());
        studentClassModel.setLabel(studentClassWithCoursesDto.getStudentClass().getLabel());
        studentClassModel.setSchool(school);
        List<Course> studentCourses = studentClassWithCoursesDto.getCourses().stream().map(courseDto -> {
            Course course = new Course();
            course.setName(courseDto.getCourseName());
            course.setDescription(courseDto.getDescription());
            Teacher teacher = new Teacher();
            teacher.setId(courseDto.getTeacher().getId());
            course.setTeacher(teacher);
            course.setStudentClass(studentClassModel);
            return course;
        }).collect(Collectors.toList());
        studentClassModel.setCourses(studentCourses);
        return studentClassModel;
    }
    @Data
    @NoArgsConstructor
    private static class StudentClassDTO {
        @NotNull
        private Integer grade;
        private Character label;
    }
    @Data
    @NoArgsConstructor
    private static class CourseDTO {
        private String courseName;
        private String description;
        private TeacherDTO teacher;
    }


}
