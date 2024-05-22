package com.senlalab.eeservice.mapper;

import com.senlalab.eeservice.dto.PersonDto;
import com.senlalab.eeservice.dto.SignUpRequest;
import com.senlalab.eeservice.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring",  uses = {SalaryMapper.class})
public interface PersonMapper {

    PersonDto entityToDto(Person entity);

    Person dtoToEntity(PersonDto dto);

    Person authToEntity(SignUpRequest dto);

    List<PersonDto> entityListToDto(List<Person> entities);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }

    default Long toId(Person person) {
        if (person == null) {
            return null;
        }
        return person.getId();
    }
}
