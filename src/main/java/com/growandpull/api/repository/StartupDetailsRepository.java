package com.growandpull.api.repository;

import com.growandpull.api.model.StartupDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupDetailsRepository extends JpaRepository<StartupDetails, String> {
}
