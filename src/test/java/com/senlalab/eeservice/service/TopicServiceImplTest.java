package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.service.impl.TopicServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TopicServiceImplTest {
    @Autowired
    private TopicServiceImpl topicServiceImpl;

    @Test
    public void whenApplicationStarts_thenGetTopics() {
        List<DirectoryDto> topics = topicServiceImpl.getAllTopics();
        Assertions.assertEquals(topics.size(), 2);
        System.out.println();
        topics.forEach(System.out::println);
        System.out.println();
    }
}
