package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.SalaryDto;
import com.senlalab.eeservice.service.SalaryService;
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
@RequestMapping("/salaries")
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryService salaryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SalaryDto> getAllSalaries() {
        log.info("Received request to get all salaries");
        List<SalaryDto> salaries = salaryService.getAllSalaries();
        log.info("Returning {} salaries", salaries.size());
        return salaries;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSalary(@Valid @RequestBody SalaryDto salaryDto) {
        log.info("Received request to create salary: {}", salaryDto);
        salaryService.createSalary(salaryDto);
        log.info("Salary successfully created");
    }

}

