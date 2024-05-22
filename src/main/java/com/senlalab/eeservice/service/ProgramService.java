package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.DirectoryDto;
import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.model.Program;
import com.senlalab.eeservice.model.Topic;

import java.util.List;

/**
 * Сервис для взаимодействия с курсами
 */
public interface ProgramService {

    /**
     * Создает курс
     *
     * @param programDto - создаваемый курс
     */
    void create(ProgramDto programDto);

    /**
     * Возвращает список всех курсов в разделе
     *
     * @param topic - раздел курса
     * @return список элементов справоченика
     */
    List<DirectoryDto> getAllForList(Topic topic);

    /**
     * Возвращает список названий всех курсов по разделу
     *
     * @param topicId - ID раздела
     * @return - объект клиента с заданным ID
     */
    List<DirectoryDto> findAllByTopicId(Long topicId);

    /**
     * Возвращает курс по его ID
     *
     * @param id - ID курса
     * @return - объект клиента с заданным ID
     */
    ProgramDto getById(Long id);

    /**
     * Обновляет курс с заданным ID,
     * в соответствии с переданным курсом
     *
     * @param programDto - раздел в соответсвии с которым нужно обновить данные
     * @param id         - id курса которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(ProgramDto programDto, Long id);

    /**
     * Удаляет курс с заданным ID
     *
     * @param id - id курса, который нужно удалить
     * @return - true если раздел был удален, иначе false
     */
    boolean delete(Long id);
}
