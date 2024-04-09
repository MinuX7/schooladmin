package com.cosmind.schooladmin.repository;

import com.cosmind.schooladmin.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {

    Optional<StudentClass> findBySchoolIdAndId(Long schoolId, Long id );
}
