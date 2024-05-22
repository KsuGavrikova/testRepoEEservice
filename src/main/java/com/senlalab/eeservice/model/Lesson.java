package com.senlalab.eeservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "lessons")
public class Lesson extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "start_date_and_time")
    private LocalDateTime startDateAndTime;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
}
