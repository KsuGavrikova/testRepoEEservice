package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.PersonDto;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.mapper.PersonMapper;
import com.senlalab.eeservice.model.Person;
import com.senlalab.eeservice.repository.PersonRepository;
import com.senlalab.eeservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    public List<PersonDto> getAllPerson() {
    List<Person> persons = personRepository.findAll();
        log.info("Retrieved {} persons", persons.size());
        return persons.isEmpty() ? Collections.emptyList() : personMapper.entityListToDto(persons);
    }

    public PersonDto getPersonById(Long authorId) {
        Person person = personRepository.findById(authorId)
                .orElseThrow(() -> new EntryNotFoundException("Person", authorId));
        log.info("Retrieved person: {}", person);
        return personMapper.entityToDto(person);}

    @Override
    public void create(PersonDto personDto) {
        personRepository.save(personMapper.dtoToEntity(personDto));
        log.info("Person {} was created", personDto);
    }

    @Override
    @Transactional
    public boolean updatePerson(PersonDto personDto, Long id) {
        if (personRepository.findById(id).isPresent()) {
            Person newPerson = personMapper.dtoToEntity(personDto);
            newPerson.setId(id);
            personRepository.save(newPerson);
            log.info("Person {} was updated", personDto);
            return true;
        }
        log.info("Person {} was not updated", personDto);
        throw new EntryNotFoundException("Person", id);
    }
}
