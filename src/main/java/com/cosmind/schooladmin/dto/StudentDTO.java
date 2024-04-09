package com.cosmind.schooladmin.dto;


import com.cosmind.schooladmin.model.Gender;
import com.cosmind.schooladmin.model.Student;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class StudentDTO {
    private Long id;
    private String photoFileName;
    private String profilePictureData;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    private String fatherName;
    private String motherName;
    private String registrationNumber;
    private LocalDate birthDate;
    private String gender;
    private List<String> hobbies;

    public static Student toStudentModel(StudentDTO studentDTO) {
        Student student = new Student();
        student.setPhotoFile(studentDTO.getPhotoFileName());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhoneNumber());
        student.setRegistrationNumber(studentDTO.getRegistrationNumber());
        student.setBirthDate(studentDTO.getBirthDate());
        student.setGender(Gender.valueOf(studentDTO.getGender()));
        student.setFatherName(studentDTO.getFatherName());
        student.setMotherName(studentDTO.getMotherName());
        String hobbies =  (studentDTO.getHobbies() != null && !studentDTO.getHobbies().isEmpty()) ?
                String.join(",", studentDTO.getHobbies()): null;
        student.setHobbies(hobbies);
        student.setClasses(new HashSet<>());
        return student;
    }

    public static StudentDTO toStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setPhotoFileName(student.getPhotoFile());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhoneNumber(student.getPhone());
        studentDTO.setRegistrationNumber(student.getRegistrationNumber());
        studentDTO.setBirthDate(student.getBirthDate());
        studentDTO.setGender(student.getGender().name());
        studentDTO.setFatherName(student.getFatherName());
        studentDTO.setMotherName(student.getMotherName());
        List<String> hobbies = student.getHobbies() != null ? Arrays.asList(student.getHobbies().split(",")) : null;
        studentDTO.setHobbies(hobbies);
        return studentDTO;
    }
}
