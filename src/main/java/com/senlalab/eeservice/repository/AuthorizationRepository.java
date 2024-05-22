package com.senlalab.eeservice.repository;

import com.senlalab.eeservice.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    Optional<Authorization> findByLogin(String login);
}
