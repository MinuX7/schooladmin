package com.cosmind.schooladmin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "course")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToOne()
    @JoinColumn(name = "teacher_id", nullable=false, referencedColumnName = "id")
    private Teacher teacher;
    @ManyToOne()
    @JoinColumn(name = "class_id", nullable=false, referencedColumnName = "id")
    private StudentClass studentClass;

}
