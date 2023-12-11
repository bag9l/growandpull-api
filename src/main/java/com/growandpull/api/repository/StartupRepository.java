package com.growandpull.api.repository;

import com.growandpull.api.model.entity.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupRepository extends JpaRepository<Startup, String> {
}
