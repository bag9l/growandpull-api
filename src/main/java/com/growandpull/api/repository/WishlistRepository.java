package com.growandpull.api.repository;

import com.growandpull.api.model.Startup;
import com.growandpull.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishListItem, String> {
    boolean existsByUserAndStartup(User user, Startup startup);

    WishListItem findByUser(User user);
}
