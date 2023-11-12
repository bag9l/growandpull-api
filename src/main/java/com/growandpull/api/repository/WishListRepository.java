package com.growandpull.api.repository;

import com.growandpull.api.model.User;
import com.growandpull.api.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<Wishlist, String> {
    List<Wishlist> findByUser(User user);

}
