package com.senlalab.eeservice.security;

import com.senlalab.eeservice.dto.JwtAuthenticationResponse;
import com.senlalab.eeservice.dto.SignUpRequest;
import com.senlalab.eeservice.dto.SignInRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
