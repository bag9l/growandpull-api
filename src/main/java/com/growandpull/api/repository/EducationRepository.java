package com.growandpull.api.repository;

import com.growandpull.api.model.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, String> {
}
