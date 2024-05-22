package com.senlalab.eeservice.repository;

import com.senlalab.eeservice.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    //TODO перевести все репы на Optional
    List<Program> findAllByTopicId(Long topicId);
}
