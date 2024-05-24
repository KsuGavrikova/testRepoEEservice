package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.ScheduleDto;
import com.senlalab.eeservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/{person_id}")
    public ResponseEntity<List<ScheduleDto>> readSchedule(@PathVariable("person_id") Long id) {
        return ResponseEntity.ok(subscriptionService.getSchedule(id));
    }
}
