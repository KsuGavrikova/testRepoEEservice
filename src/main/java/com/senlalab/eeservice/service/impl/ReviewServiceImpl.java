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
    public List<ReviewDto> getAll() {
        return reviewMapper.entityListToDto(reviewRepository.findAll());
    }

    @Override
    public void create(ReviewDto reviewDto) {
        reviewRepository.save(reviewMapper.dtoToEntity(reviewDto));
        log.warn("Review " + reviewDto + " was create");
    }
}
