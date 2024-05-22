package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.ScheduleDto;
import com.senlalab.eeservice.facade.ScheduleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final ScheduleFacade scheduleFacade;

    @GetMapping("/{person_id}")
    public ResponseEntity<List<ScheduleDto>> getSchedule(@PathVariable("person_id") Long id) {
        return new ResponseEntity<>(scheduleFacade.getSchedule(id), HttpStatus.OK);
    }
}
