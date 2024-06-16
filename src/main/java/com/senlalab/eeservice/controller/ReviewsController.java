package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.ReviewDto;
import com.senlalab.eeservice.service.ReviewService;
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
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewsController {
    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto createReview(@Valid @RequestBody ReviewDto reviewDto) {
        log.info("Received request to create review: {}", reviewDto);
        reviewService.createReview(reviewDto);
        log.info("Review created successfully: {}", reviewDto);
        return reviewDto;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDto> getAllReviews() {
        List<ReviewDto> reviews = reviewService.getAllReviews();
        log.info("Retrieved {} reviews", reviews.size());
        return reviews;
    }
}
