package com.cosmind.schooladmin.controller;

import com.cosmind.schooladmin.client.ExternalService;
import com.cosmind.schooladmin.dto.StudentClassFullDetailsDTO;
import com.cosmind.schooladmin.dto.StudentClassDto;
import com.cosmind.schooladmin.dto.StudentClassWithCoursesDto;
import com.cosmind.schooladmin.dto.TeacherDTO;
import com.cosmind.schooladmin.model.School;
import com.cosmind.schooladmin.repository.SchoolRepository;
import com.cosmind.schooladmin.service.StudentClassService;
import com.cosmind.schooladmin.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/schools")
@CrossOrigin(origins="*")
public class SchoolController {

    Logger logger = LoggerFactory.getLogger(SchoolController.class);
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentClassService studentClassService;
    @Autowired
    private ExternalService externalService;

    @GetMapping
    public List<School> getSchools() {
        return schoolRepository.findAll();
    }

    @GetMapping(value = "/external", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getExternalData() {
        return externalService.getData();
    }

    @GetMapping(value = "/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getNotifications() {
        return externalService.getNotifications();
    }

    @PostMapping
    public School saveSchool(@RequestBody School school) {
        return schoolRepository.save(school);
    }

    @PostMapping("/{schoolId}/teachers")
    public TeacherDTO saveTeacher(@PathVariable Long schoolId, @RequestBody TeacherDTO teacher) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Saving new teacher on school with id {}", schoolId);
        return teacherService.saveTeacher(schoolId, teacher);
    }

    @GetMapping("/{schoolId}/teachers")
    public List<TeacherDTO> getSchoolTeachers(@PathVariable Long schoolId) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Fetching teachers from school with id {}", schoolId);
        return teacherService.getSchoolTeachers(schoolId);
    }

    @DeleteMapping("/{schoolId}/teachers/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long schoolId, @PathVariable Long teacherId) {
//        Teacher teacher = teacherRepository.findBySchoolIdAndId(schoolId, teacherId).orElse(null);
//        if (teacher == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        teacherRepository.delete(teacher);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{schoolId}/classes")
    public StudentClassDto saveClassRoom(@PathVariable Long schoolId, @RequestBody StudentClassDto studnetClassDto) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Saving new classrom on school with id {}", schoolId);
        return studentClassService.createStudentClass(schoolId, studnetClassDto);
    }

    @GetMapping("/{schoolId}/classes/{classId}/full")
    public StudentClassFullDetailsDTO getClassDetails(@PathVariable Long schoolId, @PathVariable Long classId) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Retriving full details of class for school with id {} and class id {}", schoolId, classId);
        return studentClassService.getClassFullDetails(schoolId, classId);
    }

    @PostMapping("/{schoolId}/classes-with-courses")
    public StudentClassDto createClassWithCourses(@PathVariable Long schoolId, @RequestBody StudentClassWithCoursesDto studentClasswithCourses) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Saving new classrom with courses on school with id {}", schoolId);
        logger.info("Class with courses value", studentClasswithCourses.toString());
        return studentClassService.createStudentClassWithCourses(schoolId, studentClasswithCourses);
    }

    @GetMapping("/{schoolId}/classes")
    public List<StudentClassDto> getSchoolStudentClasses(@PathVariable Long schoolId) {
        MDC.put("requestId", UUID.randomUUID().toString());
        logger.info("Fetching all student classes from school with id {}", schoolId);
        return studentClassService.getSchoolClasses(schoolId);
    }

    @DeleteMapping("/{schoolId}/student-classes/{classId}")
    public ResponseEntity<Void> deleteStudentClass(@PathVariable Long schoolId, @PathVariable Long classId) {
//        Teacher teacher = studentClassService.delete(schoolId, teacherId).orElse(null);
//        if (teacher == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        teacherRepository.delete(teacher);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

