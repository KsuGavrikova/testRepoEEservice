package com.senlalab.eeservice.facade;

import com.senlalab.eeservice.dto.DirectoryDto;
import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.dto.TopicDto;
import com.senlalab.eeservice.service.ProgramService;
import com.senlalab.eeservice.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DirectoryFacade {

    private final TopicService topicService;
    private final ProgramService programService;

    public List<DirectoryDto> getAllProgramByTopic(Long topicId) { //todo  разделить на два сервиса + новая dto с лист
        List<DirectoryDto> allElementToTopic = new ArrayList<>();

        List<DirectoryDto> programList = programService.findAllByTopicId(topicId);
        List<DirectoryDto> topicList = topicService.getSubordinates(topicId);

        allElementToTopic.addAll(topicList);
        allElementToTopic.addAll(programList);

        return allElementToTopic;
    }

    public List<DirectoryDto> getRoot() {
        return topicService.getRoot();
    }

    public void createTopic(TopicDto topicDto) {
        topicService.create(topicDto);
    }

    public void deleteTopic(Long topicId) {
        topicService.delete(topicId);
    }

    public void createProgram(ProgramDto programDto) {
        programService.create(programDto);
    }

    public ProgramDto getProgram(Long id) {
        return programService.getById(id);
    }

    public boolean updateTopic(TopicDto topicDto, Long id) {
        return topicService.update(topicDto, id);
    }

    public boolean updateProgram(ProgramDto programDto, Long id) {
        return programService.update(programDto, id);
    }

    public boolean deleteProgram(Long id) {
        return programService.delete(id);
    }

}
