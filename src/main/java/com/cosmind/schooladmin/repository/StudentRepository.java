package com.cosmind.schooladmin.repository;

import com.cosmind.schooladmin.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
