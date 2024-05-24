package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.DirectoryDto;
import com.senlalab.eeservice.dto.TopicDto;
import com.senlalab.eeservice.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto topicDto) {
        topicService.create(topicDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicDto);
    }

    @GetMapping
    public ResponseEntity<List<DirectoryDto>> readRootTopics() {
        return ResponseEntity.ok(topicService.getRoot());
    }


    @PostMapping("/{topic_id}")
    public ResponseEntity<TopicDto> updateTopic(@PathVariable(name = "topic_id") Long id,
                                                @RequestBody TopicDto topicDto) {
        topicService.update(topicDto, id);
        return ResponseEntity.ok(topicDto);
    }

    @DeleteMapping("/{topic_id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("topic_id") Long id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
