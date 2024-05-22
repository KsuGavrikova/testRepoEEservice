package com.senlalab.eeservice.security.impl;

import com.senlalab.eeservice.repository.AuthorizationRepository;
import com.senlalab.eeservice.security.UserSecurity;
import com.senlalab.eeservice.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthorizationRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> new UserSecurity(userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
