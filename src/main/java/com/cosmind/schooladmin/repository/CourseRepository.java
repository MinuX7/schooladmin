package com.cosmind.schooladmin.repository;

import com.cosmind.schooladmin.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
