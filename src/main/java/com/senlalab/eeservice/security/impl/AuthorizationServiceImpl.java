package com.senlalab.eeservice.security.impl;

import com.senlalab.eeservice.dto.JwtAuthenticationResponse;
import com.senlalab.eeservice.dto.request.SignInRequest;
import com.senlalab.eeservice.dto.request.SignUpRequest;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.mapper.PersonMapper;
import com.senlalab.eeservice.model.Authorization;
import com.senlalab.eeservice.model.Role;
import com.senlalab.eeservice.repository.AuthorizationRepository;
import com.senlalab.eeservice.repository.PersonRepository;
import com.senlalab.eeservice.security.AuthenticationService;
import com.senlalab.eeservice.security.JwtService;
import com.senlalab.eeservice.security.UserSecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthenticationService {
    private final AuthorizationRepository authorizationRepository;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PersonMapper personMapper;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = new Authorization();
        Role role;
        user.setLogin(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        role = authorizationRepository.findAll().isEmpty() ? Role.ROLE_ADMIN : Role.ROLE_USER;
        user.setRole(role);
        authorizationRepository.save(user);

        var person = personMapper.authToEntity(request);
        person.setAuthorizationId(authorizationRepository.findByLogin(request.getEmail()).orElseThrow(EntryNotFoundException::new));
        person.setFullName(request.getFirstName() + " " + request.getLastName());
        personRepository.save(person);

        var jwt = jwtService.generateToken(new UserSecurity(user));
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = authorizationRepository.findByLogin(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(new UserSecurity(user));
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
