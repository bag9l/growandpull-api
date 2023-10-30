package com.budspark.api.repository;

import com.budspark.api.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupRepository extends JpaRepository<Startup, String> {
}
