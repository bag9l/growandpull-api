package com.growandpull.api.dto.wishlist;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.dto.user.UserCard;
import com.growandpull.api.model.StartupStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record WishlistResponse(
        String title,
        UserCard owner,
        byte[] image,
        String description,
        StartupStatus status,
        String category,
        FinanceDto finance,
        LocalDateTime createdAt
) {
}
