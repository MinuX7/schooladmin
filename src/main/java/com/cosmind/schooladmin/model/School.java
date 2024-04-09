package com.cosmind.schooladmin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "school")
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "short_name", nullable = false)
    private String shortName;
    private String name;
    @OneToMany(mappedBy = "school")
    private List<Teacher> teacherList;
    @OneToMany(mappedBy = "school")
    private List<StudentClass> classList;
}
