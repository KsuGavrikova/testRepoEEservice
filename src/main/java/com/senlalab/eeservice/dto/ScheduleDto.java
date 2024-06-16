package com.senlalab.eeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ScheduleDto {

    private String programName;

    private LocalDateTime lessonStart;
}
