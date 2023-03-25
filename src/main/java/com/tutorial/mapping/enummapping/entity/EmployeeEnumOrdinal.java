package com.tutorial.mapping.enummapping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "employee_ordinal", schema = "enumeration")
public class EmployeeEnumOrdinal {

    /**
     * Kita akan belajar Mapping ORM Hibernate, kali ini Enum Mapping
     * @Enumerated(EnumType.ORDINAL) // @Enumerated cara untuk memetakan enum. ada 2 value
     *     // ORDINAL -> urutan disimpan sesuai dengan posisi enum, di class Enum (dimulai dari 0) mirip dengan index array
     *     // STRING -> disimpan sesuai dengan nama nilai enum, di class enum.. sesuai nama yang di set enum
     *
     *     biasanya digunakan untuk status (flagging)
     */

    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "employee_name")
    private String name;
    @Column(name = "employee_tgllahir")
    private LocalDate tglLahir;

    @Enumerated(EnumType.STRING) // @Enumerated cara untuk memetakan enum. ada 2 value
    // ORDINAL -> urutan disimpan sesuai dengan posisi enum, di class Enum
    // STRING -> disimpan sesuai dengan nama nilai enum (Abjad A-Z), di class enum
    @Column(name = "employee_status")
    private EmployeeStatus status;




}
