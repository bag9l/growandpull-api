package com.growandpull.api.repository;

import com.growandpull.api.model.entity.Startup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StartupRepository extends JpaRepository<Startup, String> {

    List<Startup> findAllByOwnerEmailAndCreatedAtAfter(@Param("email") String email,
                                                       @Param("threeMonthsAgo") LocalDateTime threeMonthsAgo);
}
