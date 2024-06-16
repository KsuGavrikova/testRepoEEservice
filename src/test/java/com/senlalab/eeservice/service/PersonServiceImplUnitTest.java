package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.PersonDto;
import com.senlalab.eeservice.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PersonServiceImplUnitTest {
    @Autowired
    private PersonServiceImpl personServiceImpl;

    @Test
    public void whenApplicationStarts_thenGetUsers() {
        List<PersonDto> persons = personServiceImpl.getAllPerson();
        Assertions.assertEquals(persons.size(), 2);
        System.out.println();
        persons.forEach(System.out::println);
        System.out.println();
    }
}
