package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.LessonDto;
import com.senlalab.eeservice.service.LessonService;
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
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<LessonDto>> readAll() {
        return ResponseEntity.ok(lessonService.getAll());
    }

    @PostMapping
    public ResponseEntity<LessonDto> create(@RequestBody LessonDto lessonDto) {
        lessonService.create(lessonDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonDto);
    }

    @PostMapping("/{lesson_id}")
    public ResponseEntity<LessonDto> update(@PathVariable(name = "lesson_id") Long id, @RequestBody LessonDto lessonDto) {
        lessonService.update(lessonDto, id);
        return ResponseEntity.ok(lessonDto);
    }

    @DeleteMapping("/{lesson_id}")
    public ResponseEntity<Void> delete(@PathVariable("lesson_id") Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
