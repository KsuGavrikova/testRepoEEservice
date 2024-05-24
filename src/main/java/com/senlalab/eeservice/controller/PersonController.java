package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.PersonDto;
import com.senlalab.eeservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDto>> getPerson() {
        return ResponseEntity.ok(personService.getAll());
    }

    @PostMapping("/{person_id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable(name = "person_id") Long id,
                                                  @RequestBody PersonDto personDto) {
        personService.update(personDto, id);
        return ResponseEntity.ok(personDto);
    }

}
