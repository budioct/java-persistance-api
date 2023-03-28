package com.tutorial.query.mapping.onetomany.entity;

import com.tutorial.query.mapping.onetomany.entity.Employee;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "manager", schema = "single_table")
@SequenceGenerator(
        name = "seq_generator_manager",
        schema = "generator",
        allocationSize = 1,
        sequenceName = "seq_manager",
        initialValue = 1)
public class Manager {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_generator_manager") // sama dengan name @SequenceGenerator(name = "seq_generator_manager")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "street_address")
    private String streetAddress;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @OneToMany(mappedBy = "manager") // String mappedBy() // supaya merujuk pada relasinya.. nama nya harus sama object reference pada List<Employee>..  @ManyToOn private Manager kelas;
    List<Employee> employees = new ArrayList<>();





}
