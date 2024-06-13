package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.TopicDto;
import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicDto createTopic(@Valid @RequestBody TopicDto topicDto) {
        log.info("Received request to create topic: {}", topicDto);
        topicService.createTopic(topicDto);
        log.info("Topic created successfully: {}", topicDto);
        return topicDto;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DirectoryDto> getRootTopics() {
        log.info("Received request to get root topics");
        List<DirectoryDto> rootTopics = topicService.getRootTopics();
        log.info("Retrieved {} root topics", rootTopics.size());
        return rootTopics;
    }

    @PostMapping("/{topic_id}")
    @ResponseStatus(HttpStatus.OK)
    public TopicDto updateTopic(@PathVariable(name = "topic_id") Long id,
                                @Valid @RequestBody TopicDto topicDto) {
        log.info("Received request to update topic with id {}: {}", id, topicDto);
        topicService.updateTopic(topicDto, id);
        log.info("Topic with id {} updated successfully", id);
        return topicDto;
    }

    @DeleteMapping("/{topic_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable("topic_id") Long id) {
        log.info("Received request to delete topic with id {}", id);
        topicService.deleteTopic(id);
        log.info("Topic with id {} deleted successfully", id);
    }
}
