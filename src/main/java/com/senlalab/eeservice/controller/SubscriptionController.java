package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.SubscribeDto;
import com.senlalab.eeservice.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSubscription(@Valid @RequestBody SubscribeDto subscribeDto) {
        log.info("Received request to create subscription: {}", subscribeDto);
        subscriptionService.createSubscription(subscribeDto);
        log.info("Subscription successfully created for: {}", subscribeDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubscribeDto> getAllSubscriptions() {
        log.info("Received request to get subscriptions");
        List<SubscribeDto> subscriptions = subscriptionService.getAllSubscriptions();
        log.info("Retrieved {} subscriptions", subscriptions.size());
        return subscriptions;
    }
}
