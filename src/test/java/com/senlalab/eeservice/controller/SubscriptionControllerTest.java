package com.senlalab.eeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senlalab.eeservice.dto.ScheduleDto;
import com.senlalab.eeservice.dto.SubscribeDto;
import com.senlalab.eeservice.security.JwtService;
import com.senlalab.eeservice.security.UserService;
import com.senlalab.eeservice.service.SubscriptionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubscriptionController.class)
@AutoConfigureMockMvc
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private SubscriptionService subscriptionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Create Subscription - Success")
    @WithMockUser(username = "username", roles = "USER")
    void createSubscription_Success() throws Exception {
        SubscribeDto request = SubscribeDto.builder()
                .personId(1L)
                .programId(1L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(7))
                .build();

        mockMvc.perform(post("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Get All Subscriptions - Success")
    @WithMockUser(username = "username", roles = "USER")
    void getAllSubscriptions_Success() throws Exception {
        List<SubscribeDto> subscriptions = new ArrayList<>();
        subscriptions.add(SubscribeDto.builder()
                .personId(1L)
                .programId(100L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(30))
                .build());

        subscriptions.add(SubscribeDto.builder()
                .personId(2L)
                .programId(200L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(30))
                .build());

        when(subscriptionService.getAllSubscriptions()).thenReturn(subscriptions);

        mockMvc.perform(get("/subscriptions")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(subscriptions.size()));
    }

    @Test
    @DisplayName("Get Schedule for Person - Success")
    @WithMockUser(username = "username", roles = "USER")
    void getSchedule_ReturnsScheduleForPerson() throws Exception {
        Long personId = 1L;
        List<ScheduleDto> expectedSchedule = new ArrayList<>();

        when(subscriptionService.getSchedule(anyLong())).thenReturn(expectedSchedule);

        mockMvc.perform(get("/schedule/{person_id}", personId)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andReturn();
    }
}
