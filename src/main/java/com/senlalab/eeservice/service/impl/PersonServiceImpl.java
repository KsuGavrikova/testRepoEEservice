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

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    //TODO переделать и доделать
    public List<PersonDto> getAll() {
//        if(Collections.isEmpty())
//            throw new
        return personMapper.entityListToDto(personRepository.findAll());
    }

    public PersonDto getById(Long authorId) {
        return personMapper.entityToDto(personRepository.findById(authorId)
                .orElseThrow(() -> new EntryNotFoundException("Person", authorId)));
    }

    @Override
    public void create(PersonDto personDto) {
        personRepository.save(personMapper.dtoToEntity(personDto));
        log.info("Person " + personDto + " was created");
    }

    @Override
    public boolean update(PersonDto personDto, Long id) {
        if (personRepository.findById(id).isPresent()) {
            Person newPerson = personMapper.dtoToEntity(personDto);
            newPerson.setId(id);
            personRepository.save(newPerson);
            // TODO: 19.05.2024 не использовать warn для обычной информации
            log.info("Person " + personDto + " was update");
            return true;
        }
        log.warn("Person " + personDto + " no update");
        throw new EntryNotFoundException("Person", id);
    }
}
