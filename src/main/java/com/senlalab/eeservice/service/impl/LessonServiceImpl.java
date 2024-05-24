package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.LessonDto;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.exception.MyRepoException;
import com.senlalab.eeservice.mapper.LessonMapper;
import com.senlalab.eeservice.model.Lesson;
import com.senlalab.eeservice.repository.LessonRepository;
import com.senlalab.eeservice.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public List<LessonDto> getAll() {
        return lessonMapper.entityListToDTO(lessonRepository.findAll());
    }

    @Override
    public void create(LessonDto lessonDto) {
        try {
            lessonRepository.save(lessonMapper.dtoToEntity(lessonDto));
            log.info("Lesson saved {}", lessonDto);
        } catch (MyRepoException e) {
            log.error("Error during save lesson {}", lessonDto, e);
        }
    }

    @Override
    public boolean update(LessonDto lessonDto, Long id) {
        if (lessonRepository.findById(id).isPresent()) {
            Lesson newLesson = lessonMapper.dtoToEntity(lessonDto);
            newLesson.setId(id);
            lessonRepository.save(newLesson);
            log.warn("Lesson " + lessonDto + " was update");
            return true;
        }
        log.warn("Lesson " + lessonDto + " no update");
        throw new EntryNotFoundException("Lesson", id);
    }

    @Override
    public boolean delete(Long id) {
        lessonRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("Lesson", id));
        lessonRepository.deleteById(id);
        return true;
    }

    @Override
    public List<LessonDto> getAllByProgramId(Long programId) {
        return lessonMapper.entityListToDTO(lessonRepository.findAllByProgramId(programId)
                .orElseThrow(() -> new EntryNotFoundException("Program", programId)));
    }

}
