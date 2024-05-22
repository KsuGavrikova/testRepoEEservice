package com.senlalab.eeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salaries")
public class Salary extends BaseEntity {

    @Column(name = "is_fixed")
    private boolean isFixed;

    @Column(name = "amount")
    private Double amount;
}
