package com.cosmind.schooladmin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student_class")
@Data
@EqualsAndHashCode(exclude =  {"school", "courses", "students"})
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "label")
    private Character label;

    @ManyToOne()
    @JoinColumn(name="school_id", nullable=false)
    private School school;

    @OneToMany(mappedBy = "studentClass", cascade = CascadeType.PERSIST)
    private List<Course> courses;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_studentclass_mapping",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;
}
