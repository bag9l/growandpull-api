package com.growandpull.api.controller;

import com.growandpull.api.dto.startup.StartupCard;
import com.growandpull.api.service.FavoriteStartupsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/favorite")
public class FavoriteStartupsController {
    private final FavoriteStartupsService favoriteStartupsService;

    @PostMapping("/{startupId}")
    public void addStartupToFavorite(@AuthenticationPrincipal UserDetails user,
                                     @PathVariable("startupId") String id) {
        favoriteStartupsService.addStartupToFavorite(user.getUsername(), id);
    }

    @GetMapping()
    public ResponseEntity<Set<StartupCard>> getStartups(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                favoriteStartupsService.findFavoriteByUser(user.getUsername())
        );
    }

    @DeleteMapping("/{startupId}")
    public void deleteStartupFromFavorite(@AuthenticationPrincipal UserDetails user,
                                          @PathVariable("startupId") String id) {
        favoriteStartupsService.deleteFromFavorite(user.getUsername(), id);

    }
}
