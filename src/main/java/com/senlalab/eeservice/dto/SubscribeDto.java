package com.senlalab.eeservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscribeDto {

    private Long personId;

    private Long programId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
