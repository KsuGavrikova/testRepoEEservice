package com.senlalab.eeservice.repository;

import com.senlalab.eeservice.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    Optional<List<Program>> findAllByTopicId(Long topicId);
}
