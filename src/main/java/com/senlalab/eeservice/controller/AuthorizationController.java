package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.model.Role;
import com.senlalab.eeservice.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class AuthorizationController {
    @GetMapping
    public ResponseEntity<String> sayHello(@AuthenticationPrincipal UserSecurity userDetails) {
        System.out.println("User Details: " + userDetails.getUsername() +
                " with role: " + userDetails.getAuthorities()
                .contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name())));
        return ResponseEntity.ok(
                "Here is your resource " +
                        userDetails.getAuthorities()
                                .stream()
                                .findFirst()
                                .orElseThrow());
    }
}
