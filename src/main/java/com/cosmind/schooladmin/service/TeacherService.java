package com.cosmind.schooladmin.service;

import com.cosmind.schooladmin.dto.FileResponseDto;
import com.cosmind.schooladmin.dto.TeacherDTO;
import com.cosmind.schooladmin.model.School;
import com.cosmind.schooladmin.model.Teacher;
import com.cosmind.schooladmin.repository.SchoolRepository;
import com.cosmind.schooladmin.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private FileService fileService;

    public List<TeacherDTO> getSchoolTeachers(Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow();
        List<Teacher> teachers = school.getTeacherList();
        logger.info("Successfully fetched {} teachers from school {}", teachers.size(), school.getName());
        return teachers.stream().map(teacher -> TeacherDTO.fromTeacherModelToDto(teacher)).collect(Collectors.toList());
    }

    public TeacherDTO saveTeacher(Long schoolId, TeacherDTO teacherDTO) {
        logger.info("Birth date: {} ", teacherDTO.getBirthDate());
        School school = schoolRepository.findById(schoolId).orElseThrow();
        String photoFile = fileService.saveProfilePicture(teacherDTO.getPhotoFileName(), teacherDTO.getProfilePictureData());
        Teacher teacher = TeacherDTO.fromTeacherDtoToModel(teacherDTO);
        teacher.setPhotoFile(photoFile);
        teacher.setSchool(school);
        teacher = teacherRepository.save(teacher);
        logger.info("Successfully saved teacher with id {} on school {}", teacher.getId(), school.getName() );
        return TeacherDTO.fromTeacherModelToDto(teacher);
    }

}
