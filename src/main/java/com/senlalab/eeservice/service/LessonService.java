package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.LessonDto;

import java.util.List;

/**
 * Сервис для взаимодействия с занятиями
 */
public interface LessonService {

    /**
     * Возвращает список всех занятий
     *
     * @return список занятий
     */
    List<LessonDto> getAllLessons();

    /**
     * Создает занятие
     *
     * @param lessonDto - создаваемое занятие
     */
    void createLesson(LessonDto lessonDto);

    /**
     * Обновляет занятие с заданным ID,
     * в соответствии с переданным занятием
     *
     * @param lessonDto - занятие в соответсвии с которым нужно обновить данные
     * @param id        - id занятия, которое нужно обновить
     */
    void updateLesson(LessonDto lessonDto, Long id);

    /**
     * Удаляет занятие с заданным ID
     *
     * @param id - id занятия, которое нужно удалить
     */
    void deleteLesson(Long id);

    /**
     * Возвращает список всех занятий  по программе
     *
     * @param programId - ID программы
     * @return список занятий
     */
    List<LessonDto> getAllLessonsByProgramId(Long programId);
}
