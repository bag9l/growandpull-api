package com.growandpull.api.service.impl;

import com.growandpull.api.dto.wishlist.WishlistRequest;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.model.Startup;
import com.growandpull.api.repository.StartupRepository;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.repository.WishlistRepository;
import com.growandpull.api.service.StartupService;
import com.growandpull.api.service.UserService;
import com.growandpull.api.service.WishlistService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Service
public class WishListServiceImpl implements WishlistService {
    private static final String STARTUP_WITH_ID_NOT_FOUND = "Startup with id:%s not found";
    private final StartupRepository startupRepository;

//    @Override
//    public Startup createWishlist(String userId, WishlistRequest wishlistRequest) throws IOException {
////        Optional<Startup> startup = startupRepository.findById(wishlistRequest.getStartupId());
////        if (startup.isEmpty()) {
////        }
//    }

    @Override
    public Page<Startup> findWishlistBuUser(String userId, int page, int size) {
        return null;
    }

    @Override
    public void deleteWishlist(String userId, WishlistRequest wishlistRequest) {

    }
}
