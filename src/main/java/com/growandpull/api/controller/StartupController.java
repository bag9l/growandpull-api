package com.growandpull.api.controller;

import com.growandpull.api.dto.NewStartup;
import com.growandpull.api.dto.StartupCard;
import com.growandpull.api.dto.StartupView;
import com.growandpull.api.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/startups")
public class StartupController {

    private final StartupService startupService;

    @GetMapping("{id}")
    public ResponseEntity<StartupView> getStartupById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                startupService.getStartupById(id));
    }

    @GetMapping()
    public ResponseEntity<Page<StartupCard>> getStartups(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(
                startupService.findAllStartups(pageNumber)
        );
    }

    @PostMapping()
    public ResponseEntity<StartupView> createStartup(@RequestPart("startup") NewStartup startup,
                                                     @AuthenticationPrincipal UserDetails user) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                startupService.createStartup(startup, user.getUsername())
        );
    }
}
