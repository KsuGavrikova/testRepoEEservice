package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.PersonDto;
import com.senlalab.eeservice.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/persons")
    public List<PersonDto> getAllPersons() {
        List<PersonDto> persons = personService.getAllPerson();
        log.info("Returning {} persons", persons.size());
        return persons;
    }

    @GetMapping("/{person_id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPerson(@PathVariable(name = "person_id") Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/{person_id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(@PathVariable(name = "person_id") Long id,
                             @Valid @RequestBody PersonDto personDto) {
        log.info("Received request to update person with id: {}", id);
        personService.updatePerson(personDto, id);
        log.info("Person with id {} updated successfully", id);
    }

}
