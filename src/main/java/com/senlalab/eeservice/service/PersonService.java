package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.PersonDto;

import java.util.List;

/**
 * Сервис для взаимодействия с пользователями
 */
public interface PersonService {

    /**
     * Возвращает список всех пользователей
     *
     * @return список пользователей
     */
    List<PersonDto> getAllPerson();

    /**
     * Возвращает пользователя по ID
     *
     * @param personId - ID пользователя
     * @return элемент справочника
     */
    PersonDto getPersonById(Long personId);

    /**
     * Создает пользователя
     *
     * @param personDto - создаваемый пользователь
     */
    void create(PersonDto personDto);

    /**
     * Обновляет пользователя с заданным ID,
     * в соответствии с переданным пользователем
     *
     * @param personDto - пользователь в соответсвии с которым нужно обновить данные
     * @param id        - id пользователя, которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean updatePerson(PersonDto personDto, Long id);
}
