package com.cosmind.schooladmin.service;

import com.cosmind.schooladmin.dto.*;
import com.cosmind.schooladmin.model.Course;
import com.cosmind.schooladmin.model.School;
import com.cosmind.schooladmin.model.Student;
import com.cosmind.schooladmin.model.StudentClass;
import com.cosmind.schooladmin.repository.CourseRepository;
import com.cosmind.schooladmin.repository.SchoolRepository;
import com.cosmind.schooladmin.repository.StudentClassRepository;
import com.cosmind.schooladmin.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentClassService {

    private static final Logger logger = LoggerFactory.getLogger(StudentClassService.class);

    @Autowired
    private StudentClassRepository studentClassRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FileService fileService;

    public StudentClassDto createStudentClass(Long schoolId, StudentClassDto studentClassDto) {
        School school = schoolRepository.findById(schoolId).orElseThrow();
        StudentClass studentClass = StudentClassDto.toStudentClassModel(studentClassDto, school);
        studentClass = studentClassRepository.save(studentClass);
        logger.info("Successfully added class {} - {} to school {}.", studentClass.getGrade(), studentClass.getLabel(), school.getName());
        return StudentClassDto.toStudentClassDto(studentClass);
    }

    public StudentClassDto createStudentClassWithCourses(Long schoolId, StudentClassWithCoursesDto studentClassWithCourses) {
        School school = schoolRepository.findById(schoolId).orElseThrow();
        StudentClass studentClass = StudentClassWithCoursesDto.toStudentClassModel(studentClassWithCourses, school);
        studentClass = studentClassRepository.save(studentClass);
        String clasDispalyName = studentClass.getLabel() != null ? studentClass.getGrade().toString() + "-" + studentClass.getLabel() : studentClass.getGrade().toString();
        logger.info("Successfully added class {} with {} courses to school {}.", clasDispalyName, studentClass.getCourses().size(), school.getName());
        return StudentClassDto.toStudentClassDto(studentClass);
    }

    public List<StudentClassDto> getSchoolClasses(Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow();
        List<StudentClass> classList = school.getClassList();
        logger.info("Successfull retrieved classroom from school {}", school.getName());
        return classList.stream().map(studentClass -> StudentClassDto.toStudentClassDto(studentClass)).collect(Collectors.toList());
    }

    public StudentClassFullDetailsDTO getClassFullDetails(Long schoolId, Long classId) {
        StudentClass studentClass = studentClassRepository.findBySchoolIdAndId(schoolId, classId).orElseThrow();
        logger.info("Successfull retrieved class details from school id {} and class id", schoolId, classId);
        return StudentClassFullDetailsDTO.toStudentClassDetailsDTO(studentClass);
    }

    public List<StudentClassCourseResponseDTO> getClassCourses(Long schoolId, Long classId) {
        StudentClass studentClass = studentClassRepository.findBySchoolIdAndId(schoolId, classId).orElseThrow();
        List<StudentClassCourseResponseDTO> classCoursesResponse = studentClass.getCourses().stream().map(course -> StudentClassCourseResponseDTO.toStudentClassCourseResponseDTO(course)).collect(Collectors.toList());
        logger.info("Successfull retrieved {} courses from school id {} and class id {}", classCoursesResponse.size(), schoolId, classId);
        return classCoursesResponse;
    }

    public CourseDTO createCourse(Long schoolId, Long classId, CourseDTO courseDTO) {
        StudentClass studentClass = studentClassRepository.findBySchoolIdAndId(schoolId, classId).orElseThrow();
        Course course = CourseDTO.toCourseModel(courseDTO);
        course.setStudentClass(studentClass);
        course = courseRepository.save(course);
        logger.info("Successfull created course {} for and class {} - {}", course.getName(), studentClass.getGrade(), studentClass.getLabel());
        return CourseDTO.toCourseDTO(course);
    }

    public List<StudentDTO> getClassStudents(Long schoolId, Long classId) {
        StudentClass studentClass = studentClassRepository.findBySchoolIdAndId(schoolId, classId).orElseThrow();
        List<StudentDTO> studentListResponse = studentClass.getStudents().stream().map(student -> StudentDTO.toStudentDTO(student)).collect(Collectors.toList());
        logger.info("Successfull retrieved {} students from school id {} and class id {}", studentListResponse.size(), schoolId, classId);
        return studentListResponse;
    }

    @Transactional
    public StudentDTO createStudent(Long schoolId, Long classId, StudentDTO studentDTO) {
        Student student = StudentDTO.toStudentModel(studentDTO);
        School school = schoolRepository.findById(schoolId).orElseThrow();
        StudentClass studentClass = studentClassRepository.findBySchoolIdAndId(schoolId, classId).orElseThrow();
        student.setSchool(school);
        student.getClasses().add(studentClass);
        String photoFileName = fileService.saveProfilePicture(studentDTO.getPhotoFileName(), studentDTO.getProfilePictureData());
        student.setPhotoFile(photoFileName);
        studentClass.getStudents().add(student);
        studentClassRepository.save(studentClass);
        logger.info("Successfully created student {} {} with id {} on class {}-{}", student.getFirstName(), student.getLastName(),
                student.getId(), studentClass.getGrade(), studentClass.getLabel());
        return StudentDTO.toStudentDTO(student);
    }


}
