package com.growandpull.api.repository;

import com.growandpull.api.model.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, String> {
}
