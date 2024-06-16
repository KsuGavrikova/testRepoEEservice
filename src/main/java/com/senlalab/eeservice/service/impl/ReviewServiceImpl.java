package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.ReviewDto;
import com.senlalab.eeservice.mapper.ReviewMapper;
import com.senlalab.eeservice.repository.ReviewRepository;
import com.senlalab.eeservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getAllReviews() {
        List<ReviewDto> reviews = reviewMapper.entityListToDto(reviewRepository.findAll());
        log.info("Retrieved {} reviews", reviews.size());
        return reviews;
    }

    @Override
    public void createReview(ReviewDto reviewDto) {
        reviewRepository.save(reviewMapper.dtoToEntity(reviewDto));
        log.info("Review {} was create", reviewDto);
    }
}
