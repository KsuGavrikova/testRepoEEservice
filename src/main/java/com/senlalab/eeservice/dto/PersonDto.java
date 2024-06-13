package com.senlalab.eeservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonDto {

    @NotBlank(message = "fullName cannot be blank")
    private String fullName;

    @NotNull(message = "rating cannot be null")
    @Positive(message = "rating must be positive")
    private Integer rating;

    @NotBlank(message = "accountNumber cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "accountNumber must be a 10-digit number")
    private String accountNumber;

    @NotNull(message = "salaryId cannot be null")
    private Long salaryId;
}
