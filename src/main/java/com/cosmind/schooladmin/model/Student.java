package com.cosmind.schooladmin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="student")
@Data
@EqualsAndHashCode(exclude =  {"school", "classes"} )
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "photo_file")
    private String photoFile;
    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="phone", nullable = false)
    private String phone;
    @Column(name="registration_number", nullable = false)
    private String registrationNumber;
    @Column(name="birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name="gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name="father_name")
    private String fatherName;
    @Column(name="mother_name")
    private String motherName;
    @Column(name="hobbies")
    private String hobbies;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;
    @ManyToMany(mappedBy = "students")
    private Set<StudentClass> classes;


}
