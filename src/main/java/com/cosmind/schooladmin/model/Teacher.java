package com.cosmind.schooladmin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "teacher")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "description")
    private String description;
    @Column(name="birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "photo_file")
    private String photoFile;
    @ManyToOne
    @JoinColumn(name="school_id", nullable=false)
    private School school;
}
