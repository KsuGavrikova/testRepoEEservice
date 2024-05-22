package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.SalaryDto;
import com.senlalab.eeservice.service.SalaryService;
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
@RequestMapping("/salaries")
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryService salaryService;

    @GetMapping
    public ResponseEntity<List<SalaryDto>> read() {
        return new ResponseEntity<>(salaryService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SalaryDto salaryDto) {
        salaryService.create(salaryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
