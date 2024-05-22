package com.senlalab.eeservice.mapper;

import com.senlalab.eeservice.dto.DirectoryDto;
import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.model.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring", uses = {LessonMapper.class, PersonMapper.class, TopicMapper.class})
public interface ProgramMapper {

    @Mapping(target = "authorId", source = "entity.author")
    @Mapping(target = "topicId", source = "entity.topic")
    ProgramDto entityToDto(Program entity);

    @Mapping(source = "authorId", target = "author")
    @Mapping(source = "topicId", target = "topic")
    Program dtoToEntity(ProgramDto dto);

    List<DirectoryDto> entityListToDto(List<Program> entities);

    default Program fromId(Long id) {
        if (id == null) {
            return null;
        }
        Program program = new Program();
        program.setId(id);
        return program;
    }

    default Long toId(Program program) {
        if (program == null) {
            return null;
        }
        return program.getId();
    }

}
