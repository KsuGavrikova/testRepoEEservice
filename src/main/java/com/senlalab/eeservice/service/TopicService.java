package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.dto.TopicDto;
import com.senlalab.eeservice.model.Topic;

import java.util.List;

/**
 * Сервис для взаимодействия с разделами справочника
 */
public interface TopicService {
    /**
     * Создает раздел
     *
     * @param topicDto - создаваемый раздел
     */
    void createTopic(TopicDto topicDto);

    /**
     * Возвращает список всех имеющихся разделов
     *
     * @return список элементов справоченика
     */
    List<DirectoryDto> getAllTopics();

    /**
     * Возвращает список корневых разделов
     *
     * @return список элементов справоченика
     */
    List<DirectoryDto> getRootTopics();

    /**
     * Возвращает список подразделов передаваемого раздела
     *
     * @param parentId - ID раздела
     * @return список элементов справоченика
     */
    List<DirectoryDto> getSubordinates(Long parentId);

    /**
     * Возвращает раздел по ID
     *
     * @param topicId - ID раздела
     * @return элемент справоченика
     */
    Topic getById(Long topicId);

    /**
     * Обновляет раздел с заданным ID,
     * в соответствии с переданным разделом
     *
     * @param topic - раздел в соответсвии с которым нужно обновить данные
     * @param id    - id раздела которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean updateTopic(TopicDto topic, Long id);

    /**
     * Удаляет раздел с заданным ID
     *
     * @param id - id раздела, который нужно удалить
     */
    void deleteTopic(Long id);
}
