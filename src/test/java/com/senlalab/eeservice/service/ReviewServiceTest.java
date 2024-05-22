package com.senlalab.eeservice.service;

import com.senlalab.eeservice.model.Review;
import com.senlalab.eeservice.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReviewServiceTest {
    @Autowired
    private ReviewServiceImpl reviewService;

    @Test
    public void getReviewService() {
        List<Review> reviews = reviewService.getAll();
        Assertions.assertEquals(reviews.size(), 1);
        System.out.println();
        reviews.forEach(System.out::println);
        System.out.println();
    }
}
