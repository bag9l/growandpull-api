package com.growandpull.api.repository;

import com.growandpull.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
