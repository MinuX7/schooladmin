package com.cosmind.schooladmin.controller;

import com.cosmind.schooladmin.dto.CourseDTO;
import com.cosmind.schooladmin.dto.StudentClassCourseResponseDTO;
import com.cosmind.schooladmin.dto.StudentDTO;
import com.cosmind.schooladmin.service.StudentClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/schools/{schoolId}/classes")
@CrossOrigin(origins="*")
public class StudentClassController {
    Logger logger = LoggerFactory.getLogger(SchoolController.class);
    @Autowired
    private StudentClassService studentClassService;
    //TODO reorganize endpoints in controller.
    @GetMapping("/{classId}/courses")
    public List<StudentClassCourseResponseDTO> getStudentClassCourses(@PathVariable Long schoolId, @PathVariable Long classId) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Retriving courses for school with id {} and class id {}", schoolId, classId);
        return studentClassService.getClassCourses(schoolId, classId);
    }

    @PostMapping("/{classId}/courses")
    public CourseDTO createCourse(@PathVariable Long schoolId, @PathVariable Long classId, @RequestBody CourseDTO courseDTO) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Creating courses for school with id {} and class id {}", schoolId, classId);
        return studentClassService.createCourse(schoolId, classId, courseDTO);
    }

    @GetMapping("/{classId}/students")
    public List<StudentDTO> getStudentClassStudents(@PathVariable Long schoolId, @PathVariable Long classId) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Retriving students for school with id {} and class id {}", schoolId, classId);
        return studentClassService.getClassStudents(schoolId, classId);
    }

    @PostMapping("/{classId}/students")
    public StudentDTO createStudent(@PathVariable Long schoolId, @PathVariable Long classId, @RequestBody StudentDTO studentDTO) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Creating new student for school with id {} and class id {}.", schoolId, classId);
        return studentClassService.createStudent(schoolId, classId, studentDTO);
    }
}
