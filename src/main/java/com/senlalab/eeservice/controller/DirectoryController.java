package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.DirectoryDto;
import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.dto.TopicDto;
import com.senlalab.eeservice.facade.DirectoryFacade;
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
@RequestMapping("/directory")
@RequiredArgsConstructor
public class DirectoryController {

    private final DirectoryFacade directoryFacade;

    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody TopicDto topicDto) {
        directoryFacade.createTopic(topicDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DirectoryDto>> readRootTopics() {
        final List<DirectoryDto> rootTopics = directoryFacade.getRoot();
        return new ResponseEntity<>(rootTopics, HttpStatus.OK);
    }

    @GetMapping("/{topic_id}")
    public ResponseEntity<List<DirectoryDto>> read(@PathVariable("topic_id") Long id) {
        return new ResponseEntity<>(directoryFacade.getAllProgramByTopic(id), HttpStatus.OK);
    }

    @PostMapping("/topics/{topic_id}")
    public ResponseEntity<?> updateTopic(@PathVariable(name = "topic_id") Long id,
                                         @RequestBody TopicDto topicDto) {
        directoryFacade.updateTopic(topicDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/topics/{topic_id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("topic_id") Long id) {
        directoryFacade.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/programs")
    public ResponseEntity<?> createProgram(@RequestBody ProgramDto programDto) {
        directoryFacade.createProgram(programDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/programs/{program_id}")
    public ResponseEntity<ProgramDto> readProgram(@PathVariable("program_id") Long id) {
        return new ResponseEntity<>(directoryFacade.getProgram(id), HttpStatus.OK);
    }

    @PostMapping("/programs/{program_id}")
    public ResponseEntity<?> updateProgram(@PathVariable(name = "program_id") Long id,
                                           @RequestBody ProgramDto programDto) {
        directoryFacade.updateProgram(programDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/programs/{program_id}")
    public ResponseEntity<?> deleteProgram(@PathVariable("program_id") Long id) {
        directoryFacade.deleteProgram(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
