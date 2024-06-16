package com.senlalab.eeservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProgramDtoToUpdate {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Visibility cannot be null")
    private Boolean isVisibility;

    @NotNull(message = "Individual status cannot be null")
    private Boolean isIndividual;

    @NotNull(message = "Author ID cannot be null")
    private Long authorId;

    @NotNull(message = "Topic ID cannot be null")
    private Long topicId;

}
