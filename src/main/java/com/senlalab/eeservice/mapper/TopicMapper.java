package com.senlalab.eeservice.mapper;

import com.senlalab.eeservice.dto.DirectoryDto;
import com.senlalab.eeservice.dto.TopicDto;
import com.senlalab.eeservice.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface TopicMapper {

    Topic dtoToEntity(TopicDto dto);

    List<DirectoryDto> entityListToDto(List<Topic> entities);

    default Topic fromId(Long id) {
        if (id == null) {
            return null;
        }
        Topic topic = new Topic();
        topic.setId(id);
        return topic;
    }

    default Long toId(Topic topic) {
        if (topic == null) {
            return null;
        }
        return topic.getId();
    }
}
