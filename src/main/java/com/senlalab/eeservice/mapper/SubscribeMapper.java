package com.senlalab.eeservice.mapper;

import com.senlalab.eeservice.dto.SubscribeDto;
import com.senlalab.eeservice.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring", uses = {ProgramMapper.class, PersonMapper.class})
public interface SubscribeMapper {

    @Mapping(target = "personId", source = "person")
    @Mapping(target = "programId", source = "program")
    SubscribeDto entityToDto(Subscription entity);

    @Mapping(source = "personId", target = "person")
    @Mapping(source = "programId", target = "program")
    Subscription dtoToEntity(SubscribeDto dto);

    List<SubscribeDto> entityListToDto(List<Subscription> entities);
}
