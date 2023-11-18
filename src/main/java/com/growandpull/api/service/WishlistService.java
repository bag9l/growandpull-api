package com.growandpull.api.service;

import com.growandpull.api.dto.wishlist.WishlistRequest;
import com.growandpull.api.model.Startup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WishlistService {
//    Startup createWishlist(String userId, WishlistRequest wishlistRequest) throws Exception;

    Page<Startup> findWishlistBuUser(String userId, int page, int size);

    void deleteWishlist(String userId, WishlistRequest wishlistRequest);
}
