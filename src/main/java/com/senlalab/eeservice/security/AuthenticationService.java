package com.senlalab.eeservice.security;

import com.senlalab.eeservice.dto.JwtAuthenticationResponse;
import com.senlalab.eeservice.dto.request.SignInRequest;
import com.senlalab.eeservice.dto.request.SignUpRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
