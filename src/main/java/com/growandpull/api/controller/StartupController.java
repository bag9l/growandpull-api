package com.growandpull.api.controller;

import com.growandpull.api.dto.startup.*;
import com.growandpull.api.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

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
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                startupService.findAllStartups(pageNumber, Optional.ofNullable(user))
        );
    }

    @GetMapping("creation-data")
    public ResponseEntity<StartupCreationData> getCreationData(){
        return ResponseEntity.status(HttpStatus.OK).body(
                startupService.getCreationData());
    }

    @PostMapping()
    public ResponseEntity<StartupView> createStartup(@RequestPart("startup") StartupCreationRequest startup,
                                                     @RequestPart("image") MultipartFile image,
                                                     @AuthenticationPrincipal UserDetails user) throws IOException {
        startup.setImage(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                startupService.createStartupCheckingSubscription(startup, user.getUsername())
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<StartupView> updateStartup(@PathVariable("id") String id,
                                                     @RequestPart("updateRequest") StartupUpdateRequest startupUpdateRequest,
                                                     @RequestPart("image") MultipartFile image,
                                                     @AuthenticationPrincipal UserDetails user) throws IOException {
        startupUpdateRequest.setImage(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                startupService.updateStartup(id, startupUpdateRequest, user.getUsername())
        );
    }
}
