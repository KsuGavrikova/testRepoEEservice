package com.senlalab.eeservice.repository;

import com.senlalab.eeservice.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Optional<List<Lesson>> findAllByProgramId(Long programId);
}
