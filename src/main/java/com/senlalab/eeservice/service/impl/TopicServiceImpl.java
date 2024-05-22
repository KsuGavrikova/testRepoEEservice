package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.DirectoryDto;
import com.senlalab.eeservice.dto.TopicDto;
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

    public void create(TopicDto topicDto) {
        Topic newTopic = topicMapper.dtoToEntity(topicDto);
        topicRepository.save(newTopic);
        log.warn("Topic " + topicDto + " was create");
    }

    public List<DirectoryDto> getAll() {
        return topicMapper.entityListToDto(topicRepository.findAll());
    }

    public List<DirectoryDto> getRoot() {
        List<DirectoryDto> rootTopics = topicMapper.entityListToDto(topicRepository.findAll().stream()
                .filter(x -> x.getParentId() == null)
                .toList());
        return rootTopics;
    }

    public List<DirectoryDto> getSubordinates(Long parentId) {
        return topicMapper.entityListToDto(
                topicRepository.findAll().stream()
                        .filter(x -> !(x.getParentId() == null))
                        .filter(x -> x.getParentId().equals(parentId))
                        .toList());
    }

    public Topic getById(Long topicId) {
        return topicRepository.findById(topicId).orElseThrow(() -> new EntryNotFoundException("Topic", topicId));
    }

    public boolean update(TopicDto topicDto, Long id) {
        if (topicRepository.findById(id).isPresent()) {
            Topic newTopic = topicMapper.dtoToEntity(topicDto);
            newTopic.setId(id);
            topicRepository.save(newTopic);
            log.info("Topic " + topicDto + " was update");
            return true;
        }
        log.warn("Topic " + topicDto + " no update");
        throw new EntryNotFoundException("Topic", id);
    }

    public void delete(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Topic ",  id));
        topicRepository.delete(topic);
//        if (topicRepository.findById(id).isPresent()) {
//            topicRepository.deleteById(id);//todo нужна repo exception (? как в ProgramService )
//            resetParent(id);
//            log.info("Topic with id " + id + " was delete");
//            return true;
//        }
//        log.warn("Topic with id " + id + " no delete");
//        throw new EntryNotFoundException("Topic", id);
    }

    private void resetParent(Long id) {
        for (Topic t :
                topicRepository.findAll()) {
            if (t.getParentId().equals(id)) {
                t.setParentId(null);
            }
        }
    }
}
