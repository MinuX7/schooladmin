package com.cosmind.schooladmin.dto;

import com.cosmind.schooladmin.model.Teacher;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String description;
    private LocalDate birthDate;
    private String photoFileName;
    private String profilePictureData;

    public static TeacherDTO fromTeacherModelToDto(Teacher teacher) {
        return TeacherDTO.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .description(teacher.getDescription())
                .birthDate(teacher.getBirthDate())
                .photoFileName(teacher.getPhotoFile())
                .build();
    }

    public static Teacher fromTeacherDtoToModel(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setDescription(teacherDTO.getDescription());
        teacher.setPhotoFile(teacherDTO.getPhotoFileName());
        teacher.setBirthDate(teacherDTO.getBirthDate());
        return teacher;
    }
}
