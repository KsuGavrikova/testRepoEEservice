package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.TopicDto;
import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.mapper.TopicMapper;
import com.senlalab.eeservice.model.Topic;
import com.senlalab.eeservice.repository.TopicRepository;
import com.senlalab.eeservice.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    private final TopicMapper topicMapper;

    private final TopicRepository topicRepository;

    @Override
    public void createTopic(TopicDto topicDto) {
        Topic newTopic = topicMapper.dtoToEntity(topicDto);
        topicRepository.save(newTopic);
        log.warn("Topic " + topicDto + " was create");
    }

    @Override
    public List<DirectoryDto> getAllTopics() {
        return topicMapper.entityListToDto(topicRepository.findAll());
    }

    @Override
    public List<DirectoryDto> getRootTopics() {
        return topicMapper.entityListToDto(
                topicRepository.findAll()
                        .stream()
                        .filter(x -> x.getParentId() == null)
                        .toList());
    }

    @Override
    public List<DirectoryDto> getSubordinates(Long parentId) {
        return topicMapper.entityListToDto(
                topicRepository.findAll().stream()
                        .filter(x -> !(x.getParentId() == null))
                        .filter(x -> x.getParentId().equals(parentId))
                        .toList());
    }

    @Override
    public Topic getById(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new EntryNotFoundException("Topic", topicId));
    }

    @Override
    public boolean updateTopic(TopicDto topicDto, Long id) {
        if (topicRepository.findById(id).isPresent()) {
            Topic newTopic = topicMapper.dtoToEntity(topicDto);
            newTopic.setId(id);
            topicRepository.save(newTopic);
            log.info("Topic {} was update", topicDto);
            return true;
        }
        log.warn("Topic {} no update", topicDto);
        throw new EntryNotFoundException("Topic", id);
    }

    @Override
    public void deleteTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Topic ", id));
        topicRepository.delete(topic);
    }

}
