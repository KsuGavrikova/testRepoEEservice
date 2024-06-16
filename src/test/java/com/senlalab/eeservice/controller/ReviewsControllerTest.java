package com.senlalab.eeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senlalab.eeservice.dto.ReviewDto;
import com.senlalab.eeservice.security.JwtService;
import com.senlalab.eeservice.security.UserService;
import com.senlalab.eeservice.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewsController.class)
class ReviewsControllerTest {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Create Review - Status 201 Created")
    @WithMockUser(username = "username", roles = "USER")
    void createReview_ReturnsStatusCreated() throws Exception {
        ReviewDto reviewDto = ReviewDto.builder()
                .isPositiveFeedback(true)
                .comment("Great product")
                .programId(123L)
                .personId(456L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewDto))
                        .with(csrf()))
                .andExpect(status().isCreated());

        // Захватываем переданный объект ReviewDto с помощью ArgumentCaptor
        ArgumentCaptor<ReviewDto> captor = ArgumentCaptor.forClass(ReviewDto.class);
        verify(reviewService, times(1)).createReview(captor.capture());

        // Проверяем, что захваченный объект ReviewDto соответствует ожидаемому объекту reviewDto
        assertEquals(reviewDto, captor.getValue());
    }


    @Test
    @DisplayName("Get All Reviews - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void getAllReviews_ReturnsStatusOk() throws Exception {
        List<ReviewDto> reviews = Collections.singletonList(
                ReviewDto.builder()
                        .comment("Great product")
                        .isPositiveFeedback(true)
                        .build()
        );

        when(reviewService.getAllReviews()).thenReturn(reviews);

        mockMvc.perform(MockMvcRequestBuilders.get("/reviews")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment").value("Great product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isPositiveFeedback").value(true));
    }

}
