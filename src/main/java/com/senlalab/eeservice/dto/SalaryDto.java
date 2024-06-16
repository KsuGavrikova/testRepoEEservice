package com.senlalab.eeservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class SalaryDto {

    @NotNull(message = "isFixed field cannot be null")
    private Boolean isFixed;

    @NotNull(message = "amount field cannot be null")
    @Positive(message = "amount must be positive")
    private Double amount;

}
