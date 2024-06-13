package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.LessonDto;
import com.senlalab.eeservice.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping
    public List<LessonDto> getAllLessons() {
        List<LessonDto> lessons = lessonService.getAllLessons();
        log.info("Retrieved {} lessons", lessons.size());
        return lessons;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLesson(@Valid @RequestBody LessonDto lessonDto) {
        log.info("Received request to create lesson: {}", lessonDto);
        lessonService.createLesson(lessonDto);
        log.info("Lesson created successfully: {}", lessonDto);
    }

    @PutMapping("/{lesson_id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateLesson(@PathVariable(name = "lesson_id") Long id, @Valid @RequestBody LessonDto lessonDto) {
        lessonService.updateLesson(lessonDto, id);
        log.info("Lesson updated successfully: {}", lessonDto);
    }

    @DeleteMapping("/{lesson_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLesson(@PathVariable("lesson_id") Long id) {
        lessonService.deleteLesson(id);
        log.info("Lesson with id {} deleted successfully", id);
    }
}
