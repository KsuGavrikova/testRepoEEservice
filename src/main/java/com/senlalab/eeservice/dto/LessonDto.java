package com.senlalab.eeservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LessonDto {

    private String description;

    private LocalDateTime startDateAndTime;

    private Double cost;

    private Long programId;
}
