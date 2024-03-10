package com.cosmind.schooladmin.repository;

import com.cosmind.schooladmin.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
