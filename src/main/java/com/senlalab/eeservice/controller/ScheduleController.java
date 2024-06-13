package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.ScheduleDto;
import com.senlalab.eeservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/{person_id}")
    public List<ScheduleDto> getSchedule(@PathVariable("person_id") Long id) {
        log.info("Received request to get schedule for person with id {}", id);
        List<ScheduleDto> schedule = subscriptionService.getSchedule(id);
        log.info("Retrieved schedule for person with ID {}", id);
        return schedule;
    }
}
