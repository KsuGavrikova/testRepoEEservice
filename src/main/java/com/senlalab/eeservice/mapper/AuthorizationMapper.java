package com.senlalab.eeservice.mapper;

import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.dto.request.SignUpRequest;
import com.senlalab.eeservice.model.Authorization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface AuthorizationMapper {


    Authorization dtoToEntity(SignUpRequest dto);

    @Mapping(source = "login", target = "name")
    DirectoryDto entityToDirectoryDto(Authorization entity);

    List<DirectoryDto> entityListToDto(List<Authorization> entities);
}
