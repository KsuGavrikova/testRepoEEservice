package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.dto.ProgramDtoToUpdate;

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
    void createProgram(ProgramDtoToUpdate programDto);

    /**
     * Возвращает список всех курсов в разделе
     *
     * @return список элементов справоченика
     */
    List<DirectoryDto> getVisibilityProgram();

    /**
     * Возвращает список названий всех курсов по разделу
     *
     * @param topicId - ID раздела
     * @return - объект клиента с заданным ID
     */
    List<DirectoryDto> getAllProgramByTopic(Long topicId);

    /**
     * Возвращает курс по его ID
     *
     * @param id - ID курса
     * @return - объект клиента с заданным ID
     */
    ProgramDto getProgramById(Long id);

    /**
     * Обновляет курс с заданным ID,
     * в соответствии с переданным курсом
     *
     * @param programDto - раздел в соответсвии с которым нужно обновить данные
     * @param id         - id курса которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean updateProgram(ProgramDtoToUpdate programDto, Long id);

    /**
     * Удаляет курс с заданным ID
     *
     * @param id - id курса, который нужно удалить
     * @return - true если раздел был удален, иначе false
     */
    boolean deleteProgram(Long id);

    List<DirectoryDto> getIndividualProgram();
}
