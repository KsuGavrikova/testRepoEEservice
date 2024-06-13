package com.senlalab.eeservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class LessonDto {

    @NotNull(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Start date and time cannot be null")
    private LocalDateTime startDateAndTime;

    @NotNull(message = "Cost cannot be null")
    @Positive(message = "Cost must be a positive number")
    private Double cost;

    @NotNull(message = "Program ID cannot be null")
    private Long programId;
}
