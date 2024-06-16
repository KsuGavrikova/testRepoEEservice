package com.senlalab.eeservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class TopicDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Parent ID cannot be null")
    @PositiveOrZero(message = "Parent ID must be positive or zero")
    private Long parentId;

}
