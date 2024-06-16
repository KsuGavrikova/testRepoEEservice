package com.senlalab.eeservice.repository;

import com.senlalab.eeservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<List<Subscription>> findAllByPersonId(Long personId);
}
