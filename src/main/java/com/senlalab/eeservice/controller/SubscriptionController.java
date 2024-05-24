package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.SubscribeDto;
import com.senlalab.eeservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscribeDto> create(@RequestBody SubscribeDto subscribeDto) {
        subscriptionService.create(subscribeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscribeDto);
    }

    @GetMapping
    public ResponseEntity<List<SubscribeDto>> readAll() {
        return ResponseEntity.ok(subscriptionService.getAll());

    }
}
