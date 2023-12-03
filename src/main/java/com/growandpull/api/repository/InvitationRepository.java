package com.growandpull.api.repository;

import com.growandpull.api.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, String> {
}
