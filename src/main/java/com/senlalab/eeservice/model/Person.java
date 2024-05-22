package com.senlalab.eeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "people")
public class Person extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "account_number")
    private String accountNumber;

    @OneToOne
    @JoinColumn(name = "authorization_id", referencedColumnName = "id")
    private Authorization authorizationId;

    @OneToOne
    @JoinColumn(name = "salary_id", referencedColumnName = "id")
    private Salary salaryId;
}
