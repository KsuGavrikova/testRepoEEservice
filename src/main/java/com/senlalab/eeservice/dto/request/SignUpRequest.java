package com.senlalab.eeservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotNull(message = "firstName cannot be null")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    private String lastName;

    @NotNull(message = "email cannot be null")
    private String email;

    @NotNull(message = "password cannot be null")
    private String password;
}
