package com.senlalab.eeservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "programs")
public class Program extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "is_individual")
    private Boolean isIndividual;

    @Column(name = "is_visibility")
    private Boolean isVisibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Person author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

//    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY)
//    private List<Lesson> lessons;
}
