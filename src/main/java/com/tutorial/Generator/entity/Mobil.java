package com.tutorial.Generator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "kelas_sequesnce_table", schema = "generator")
@SequenceGenerator(
        name = "seq_generator_kelas",
        schema = "generator",
        allocationSize = 1,
        sequenceName = "seq_kelas",
        initialValue = 1)
public class Mobil {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_generator_kelas") // sama dengan name @SequenceGenerator(name = "seq_gen_kelas")
    private Long id;

    @Column(name = "nama", length = 10)
    private String name;

    @Column(name = "merek", length = 30)
    private String merk;

    @Column(name = "type", length = 30)
    private String type;

    @Column(name = "create_by", length = 100, nullable = false)
    private String createBy;

    @Column(name = "created_datetime", nullable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "last_updated_by", length = 100)
    private String lastUpdateBy;

    @Column(name = "last_updated_datetime")
    private LocalDateTime lastUpdatedBy;


}
