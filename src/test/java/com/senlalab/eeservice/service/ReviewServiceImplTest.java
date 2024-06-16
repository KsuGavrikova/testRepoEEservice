package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.ReviewDto;
import com.senlalab.eeservice.mapper.ReviewMapper;
import com.senlalab.eeservice.model.Person;
import com.senlalab.eeservice.model.Program;
import com.senlalab.eeservice.model.Review;
import com.senlalab.eeservice.repository.ReviewRepository;
import com.senlalab.eeservice.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private ReviewDto reviewDto;
    private Review review;

    @BeforeEach
    void setUp() {
        reviewDto = ReviewDto.builder()
                .comment("Great product!")
                .isPositiveFeedback(true)
                .personId(1L)
                .programId(1L)
                .build();

        review = Review.builder()
                .comment("Great product!")
                .isPositiveFeedback(true)
                .person(new Person())
                .program(new Program())
                .build();
    }

    @Test
    @DisplayName("Get All Reviews - Success")
    void getAllReviews_Success() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findAll()).thenReturn(reviews);

        when(reviewMapper.entityListToDto(reviews)).thenReturn(Collections.singletonList(reviewDto));

        List<ReviewDto> returnedReviews = reviewService.getAllReviews();

        assertEquals(reviews.size(), returnedReviews.size());
    }

    @Test
    @DisplayName("Create Review - Success")
    void createReview_Success() {
        when(reviewMapper.dtoToEntity(reviewDto)).thenReturn(review);

        assertDoesNotThrow(() -> reviewService.createReview(reviewDto));

        verify(reviewRepository, times(1)).save(review);
    }
}
