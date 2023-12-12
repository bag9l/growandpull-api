package com.growandpull.api.controller;

import com.growandpull.api.dto.subscription.SubscriptionCard;
import com.growandpull.api.dto.subscription.SubscriptionDto;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import com.growandpull.api.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    @GetMapping()
    public ResponseEntity<List<SubscriptionCard>> getAllSubscriptions(){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.findAllSubscriptions());
    }

    @GetMapping("{type}")
    public ResponseEntity<SubscriptionDto> getSubscriptionByType(@PathVariable("type")SubscriptionTypeIdentifier type){
        System.out.println(type);
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.findSubscriptionByType(type));
    }
}
