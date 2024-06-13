package com.senlalab.eeservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@EqualsAndHashCode
public class ProgramDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Individual status cannot be null")
    private Boolean isIndividual;

    @NotNull(message = "Author ID cannot be null")
    private Long authorId;

    @NotNull(message = "Topic ID cannot be null")
    private Long topicId;
//todo
//    private List<LessonDto> lessons;
}
