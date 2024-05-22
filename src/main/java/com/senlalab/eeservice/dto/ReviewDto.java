package com.senlalab.eeservice.dto;

import lombok.Data;


@Data
public class ReviewDto {

    private boolean isPositiveFeedback;

    private String comment;

    private Long programId;

    private Long personId;
}
