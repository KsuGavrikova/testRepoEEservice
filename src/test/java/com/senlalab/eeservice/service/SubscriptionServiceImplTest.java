package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.SubscribeDto;
import com.senlalab.eeservice.mapper.SubscribeMapper;
import com.senlalab.eeservice.model.Subscription;
import com.senlalab.eeservice.repository.SubscriptionRepository;
import com.senlalab.eeservice.service.impl.SubscriptionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriptionServiceImplTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private SubscribeMapper subscribeMapper;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @Test
    @DisplayName("Get All Subscriptions - Success")
    void getAllSubscriptions_Success() {
        List<Subscription> subscriptions = new ArrayList<>();

        when(subscriptionRepository.findAll()).thenReturn(subscriptions);

        List<SubscribeDto> mappedSubscriptions = new ArrayList<>();
        when(subscribeMapper.entityListToDto(subscriptions)).thenReturn(mappedSubscriptions);

        List<SubscribeDto> returnedSubscriptions = subscriptionService.getAllSubscriptions();

        assertEquals(mappedSubscriptions.size(), returnedSubscriptions.size());
    }

    @Test
    @DisplayName("Get All Subscriptions By Person Id - Success")
    void getAllSubscriptionsByPersonId_Success() {
        Long personId = 123L;

        List<Subscription> subscriptions = new ArrayList<>();

        when(subscriptionRepository.findAllByPersonId(personId)).thenReturn(Optional.of(subscriptions));

        List<SubscribeDto> mappedSubscriptions = new ArrayList<>();

        when(subscribeMapper.entityListToDto(subscriptions)).thenReturn(mappedSubscriptions);

        List<SubscribeDto> returnedSubscriptions = subscriptionService.getAllByPersonId(personId);

        assertEquals(mappedSubscriptions.size(), returnedSubscriptions.size());
    }
}
