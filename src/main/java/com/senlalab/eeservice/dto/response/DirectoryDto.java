package com.senlalab.eeservice.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DirectoryDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

}
