package com.senlalab.eeservice.service;

import com.senlalab.eeservice.model.Subscription;
import com.senlalab.eeservice.service.impl.SubscriptionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SubscriptionServiceTest {
    @Autowired
    private SubscriptionServiceImpl subscriptionService;

    @Test
    public void whenApplicationStarts_thenGetSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAll();
        Assertions.assertEquals(subscriptions.size(), 1);
        System.out.println();
        subscriptions.forEach(System.out::println);
        System.out.println();
    }
}
