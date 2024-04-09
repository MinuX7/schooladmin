package com.cosmind.schooladmin.repository;

import com.cosmind.schooladmin.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findBySchoolIdAndId(Long schoolId, Long teacherrId);
}
