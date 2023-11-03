package com.budspark.api.repository;

import com.budspark.api.model.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, String> {
}
