package com.growandpull.api.repository;

import com.growandpull.api.model.User;
import com.growandpull.api.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishList, String> {
    List<WishList> findAllByUser(User user);
}
