package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.SalaryDto;

import java.util.List;

/**
 * Сервис для взаимодействия с вознаграждением преподавателям
 */
public interface SalaryService {
    /**
     * Возвращает список всех вознаграждений
     *
     * @return список подписок
     */
    List<SalaryDto> getAll();

    /**
     * Создает новое вознаграждение
     *
     * @param salaryDto - создаваемое вознаграждение
     */
    void create(SalaryDto salaryDto);
}
