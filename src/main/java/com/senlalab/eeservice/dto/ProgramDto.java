package com.senlalab.eeservice.dto;

import lombok.Data;

@Data
public class ProgramDto {

    private String name;

    private Boolean isVisibility;

    private Boolean isIndividual;

    private Long authorId;

    private Long topicId;
//todo убрать?
//    private List<LessonDto> lessons;
}
