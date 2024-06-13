package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.PersonDto;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.mapper.PersonMapper;
import com.senlalab.eeservice.model.Person;
import com.senlalab.eeservice.repository.PersonRepository;
import com.senlalab.eeservice.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceImplTest {

    @Mock
    private PersonMapper personMapper;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    private PersonDto personDto;

    @BeforeEach
    void setUp() {
        personDto = PersonDto.builder()
                .fullName("John Doe")
                .rating(5)
                .accountNumber("1234567890")
                .salaryId(1L)
                .build();
    }

    @Test
    @DisplayName("Get Person By ID - Not Found")
    void getPersonById_NotFound() {
        Long personId = 1L;

        when(personRepository.findById(personId)).thenReturn(Optional.empty());

        assertThrows(EntryNotFoundException.class, () -> personService.getPersonById(personId));
    }

    @Test
    @DisplayName("Update Person - Success")
    void updatePerson_Success() {
        Long personId = 1L;

        when(personRepository.findById(personId)).thenReturn(Optional.of(new Person()));
        when(personMapper.dtoToEntity(personDto)).thenReturn(new Person());

        boolean updated = personService.updatePerson(personDto, personId);

        assertTrue(updated);
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    @DisplayName("Update Person - Not Found")
    void updatePerson_NotFound() {
        Long personId = 1L;

        when(personRepository.findById(personId)).thenReturn(Optional.empty());

        assertThrows(EntryNotFoundException.class, () -> personService.updatePerson(personDto, personId));
    }
}
