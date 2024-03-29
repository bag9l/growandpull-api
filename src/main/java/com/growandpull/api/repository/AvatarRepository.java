package com.growandpull.api.repository;

import com.growandpull.api.model.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, String> {
}
