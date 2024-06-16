package com.senlalab.eeservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ReviewDto {

    @NotNull(message = "isPositiveFeedback field cannot be null")
    private Boolean isPositiveFeedback;

    @Size(max = 255, message = "comment must be at most 255 characters")
    private String comment;

    @NotNull(message = "programId field cannot be null")
    private Long programId;

    @NotNull(message = "personId field cannot be null")
    private Long personId;

}
