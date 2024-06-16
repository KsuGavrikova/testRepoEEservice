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

    public List<LessonDto> getAllLessons() {
        return lessonMapper.entityListToDTO(lessonRepository.findAll());
    }

    @Override
    public void createLesson(LessonDto lessonDto) {
        lessonRepository.save(lessonMapper.dtoToEntity(lessonDto));
        log.info("Lesson saved successfully: {}", lessonDto);
    }

    @Override
    public void updateLesson(LessonDto lessonDto, Long id) {
        lessonRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Lesson", id));

        Lesson newLesson = lessonMapper.dtoToEntity(lessonDto);
        newLesson.setId(id);
        lessonRepository.save(newLesson);

        log.info("Lesson with id {} updated successfully: {}", id, lessonDto);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
        log.info("Lesson with id {} deleted successfully", id);
    }

    @Override
    public List<LessonDto> getAllLessonsByProgramId(Long programId) {
        return lessonMapper.entityListToDTO(lessonRepository.findAllByProgramId(programId)
                .orElseThrow(() -> new EntryNotFoundException("Program", programId)));
    }

}
