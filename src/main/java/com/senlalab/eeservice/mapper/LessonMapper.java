package com.senlalab.eeservice.mapper;

import com.senlalab.eeservice.dto.LessonDto;
import com.senlalab.eeservice.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring", uses = ProgramMapper.class)
public interface LessonMapper {

    @Mapping(target = "programId", source = "program")
    LessonDto entityToDto(Lesson entity);

    @Mapping(source = "programId", target = "program")
    Lesson dtoToEntity(LessonDto dto);

    List<LessonDto> entityListToDTO(List<Lesson> entities);

    default Lesson fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setId(id);
        return lesson;
    }
}
