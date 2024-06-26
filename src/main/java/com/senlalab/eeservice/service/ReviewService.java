package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.ReviewDto;

import java.util.List;

/**
 * Сервис для взаимодействия c отзывами
 */
public interface ReviewService {
    /**
     * Возвращает список всех имеющихся отзывов
     *
     * @return список отзывов
     */
    List<ReviewDto> getAll();

    /**
     * Создает отзыв
     *
     * @param reviewDto - создаваемый отзыв
     */
    void create(ReviewDto reviewDto);
}
