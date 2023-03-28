package com.tutorial.query.mapping.onetomany.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "employees", schema = "single_table")
@SequenceGenerator(
        name = "seq_generator_employee",
        schema = "generator",
        allocationSize = 1,
        sequenceName = "seq_employee",
        initialValue = 1)
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_generator_employee") // sama dengan name @SequenceGenerator(name = "seq_generator_employee")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "street_address")
    private String streetAddress;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    Manager manager;


}
