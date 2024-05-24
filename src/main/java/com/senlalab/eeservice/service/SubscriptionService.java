package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.ScheduleDto;
import com.senlalab.eeservice.dto.SubscribeDto;

import java.util.List;

/**
 * Сервис для взаимодействия с подписками
 */
public interface SubscriptionService {
    /**
     * Возвращает список всех имеющихся подписок
     *
     * @return список подписок
     */
    List<SubscribeDto> getAll();

    /**
     * Создает новую подписку
     *
     * @param subscribeDto - создаваемая подписка
     */
    void create(SubscribeDto subscribeDto);

    /**
     * Возвращает список подписок для конкретного пользователя
     *
     * @param personId - ID пользователя
     * @return список подписок
     */
    List<SubscribeDto> getAllByPersonId(Long personId);
    /**
     * Возвращает расписание для конкретного пользователя
     *
     * @param personId - ID пользователя
     * @return расписание
     */
    List<ScheduleDto> getSchedule(Long personId);
}
