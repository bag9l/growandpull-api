package com.budspark.api.controller;

import com.budspark.api.dto.NewStartup;
import com.budspark.api.dto.StartupView;
import com.budspark.api.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<StartupView> createStartup(@RequestPart("startup") NewStartup newStartup,
                                                     @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                startupService.createStartup(newStartup, user.getUsername())
        );
    }
}
