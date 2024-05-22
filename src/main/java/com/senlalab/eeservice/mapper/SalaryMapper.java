package com.senlalab.eeservice.mapper;

import com.senlalab.eeservice.dto.SalaryDto;
//import com.senlalab.eeservice.model.Person;
import com.senlalab.eeservice.model.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface SalaryMapper {

    Salary dtoToEntity(SalaryDto dto);

    SalaryDto entityToDto(Salary entity);

    List<SalaryDto> entityListToDto(List<Salary> all);

    default Salary fromId(Long id) {
        if (id == null) {
            return null;
        }
        Salary salary = new Salary();
        salary.setId(id);
        return salary;
    }

    default Long toId(Salary salary) {
        if (salary == null) {
            return null;
        }
        return salary.getId();
    }
}
