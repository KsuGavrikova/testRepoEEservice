package com.senlalab.eeservice.mapper;

import com.senlalab.eeservice.dto.ReviewDto;
import com.senlalab.eeservice.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring", uses = {ProgramMapper.class, PersonMapper.class})
public interface ReviewMapper {

    @Mapping(target = "personId", source = "person")
    @Mapping(target = "programId", source = "program")
    ReviewDto entityToDto(Review entity);

    @Mapping(source = "personId", target = "person")
    @Mapping(source = "programId", target = "program")
    Review dtoToEntity(ReviewDto dto);

    List<ReviewDto> entityListToDto(List<Review> entities);

}
