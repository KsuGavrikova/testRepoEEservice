package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.ReviewDto;
import com.senlalab.eeservice.service.ReviewService;
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
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewsController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> create(@RequestBody ReviewDto reviewDto) {
        reviewService.create(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> readAll() {
        return ResponseEntity.ok(reviewService.getAll());

    }
}
