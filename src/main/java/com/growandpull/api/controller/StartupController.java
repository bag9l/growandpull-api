package com.growandpull.api.controller;

import com.growandpull.api.dto.StartupView;
import com.growandpull.api.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
