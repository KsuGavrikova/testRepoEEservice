package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.LessonDto;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.mapper.LessonMapper;
import com.senlalab.eeservice.model.Lesson;
import com.senlalab.eeservice.model.Program;
import com.senlalab.eeservice.repository.LessonRepository;
import com.senlalab.eeservice.service.impl.LessonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LessonServiceImplTest {

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private LessonMapper lessonMapper;

    @InjectMocks
    private LessonServiceImpl lessonService;

    private List<Lesson> lessonList;

    @BeforeEach
    void setUp() {
        lessonList = new ArrayList<>();
        lessonList.add(new Lesson("Introduction to Algebra", LocalDateTime.now(), 10.0, new Program()));
        lessonList.add(new Lesson("Introduction to Biology", LocalDateTime.now(), 15.0, new Program()));
    }


    @Test
    @DisplayName("Get all lessons")
    void getAllLessons() {
        when(lessonRepository.findAll()).thenReturn(lessonList);

        List<LessonDto> lessonDtoList;
        lessonDtoList = new ArrayList<>();
        lessonDtoList.add(LessonDto.builder()
                .description("Math")
                .startDateAndTime(LocalDateTime.now())
                .cost(100.0)
                .programId(1L)
                .build());

        lessonDtoList.add(LessonDto.builder()
                .description("Science")
                .startDateAndTime(LocalDateTime.now())
                .cost(150.0)
                .programId(2L)
                .build());

        when(lessonMapper.entityListToDTO(lessonList)).thenReturn(lessonDtoList);

        List<LessonDto> returnedLessonList = lessonService.getAllLessons();

        assertEquals(lessonDtoList.size(), returnedLessonList.size());
        assertEquals(lessonDtoList, returnedLessonList);
    }

    @Test
    @DisplayName("Create lesson")
    void createLesson() {
        LessonDto lessonDto = LessonDto.builder()
                .description("English")
                .startDateAndTime(LocalDateTime.now())
                .cost(100.0)
                .programId(1L)
                .build();

        Lesson lesson = Lesson.builder()
                .description("English")
                .startDateAndTime(LocalDateTime.now())
                .cost(100.0)
                .build();

        when(lessonMapper.dtoToEntity(lessonDto)).thenReturn(lesson);
        when(lessonRepository.save(lesson)).thenReturn(lesson);

        assertDoesNotThrow(() -> lessonService.createLesson(lessonDto));
        verify(lessonRepository, times(1)).save(lesson);
    }

    @Test
    @DisplayName("Update lesson")
    void updateLesson() {
        Long id = 1L;
        LessonDto lessonDto = LessonDto.builder()
                .description("Math")
                .startDateAndTime(LocalDateTime.now())
                .cost(100.0)
                .programId(1L)
                .build();

        Lesson lesson = Lesson.builder()
                .description("Math")
                .startDateAndTime(LocalDateTime.now())
                .cost(100.0)
                .build();

        // Ensure that lessonMapper.dtoToEntity(lessonDto) returns a non-null value
        when(lessonMapper.dtoToEntity(lessonDto)).thenReturn(lesson);

        when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));

        assertDoesNotThrow(() -> lessonService.updateLesson(lessonDto, id));
        verify(lessonRepository, times(1)).save(lesson);
    }


    @Test
    @DisplayName("Update lesson - lesson not found")
    void updateLesson_LessonNotFound() {
        Long id = 1L;
        LessonDto lessonDto = LessonDto.builder()
                .description("Math")
                .startDateAndTime(LocalDateTime.now())
                .cost(100.0) // Specify the cost you need
                .programId(1L)
                .build();

        when(lessonRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntryNotFoundException.class, () -> lessonService.updateLesson(lessonDto, id));
    }

    @Test
    @DisplayName("Get all lessons by program ID")
    void getAllByProgramId(List<LessonDto> returnedLessonList) {
        Long programId = 1L;

        when(lessonRepository.findAllByProgramId(programId)).thenReturn(Optional.of(lessonList));

        List<LessonDto> lessonDtoList = new ArrayList<>();
        lessonDtoList.add(LessonDto.builder()
                .description("Math")
                .startDateAndTime(LocalDateTime.now())
                .cost(100.0)
                .programId(1L)
                .build());
        lessonDtoList.add(LessonDto.builder()
                .description("Science")
                .startDateAndTime(LocalDateTime.now())
                .cost(100.0)
                .programId(1L)
                .build());

        when(lessonMapper.entityListToDTO(lessonList)).thenReturn(lessonDtoList);

        List<LessonDto> returnedLessonList = lessonService.getAllLessonsByProgramId(programId);

        assertEquals(lessonDtoList.size(), returnedLessonList.size());
        assertEquals(lessonDtoList, returnedLessonList);
    }

}
