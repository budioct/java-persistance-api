package com.tutorial.constraint.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(
        name = "kelas_unique",
        schema = "sekolah",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_kelas",
                        columnNames = {"nama", "angkatan"}
                )
        }
) //1.  harus sama persis dengan method name pada @Column, 2. jika tidak harus sama denga nama variable, 3. jika tidak harus sama dengan nama column table DB
public class Siswa {

    /**
     * Di class ini kita akan membuat unique Constraint
     * <p>
     * <p>
     * ini adalah class entity, model, java bean, POJO, Domain
     * setelah class dibuat kita harus registry ke Hibernate, di file hibernate.cfg.xml. / java configuration harus lengkap dengan package2 nya
     */

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nama", length = 10)
    private String name;

    @Column(name = "angkatan", length = 4)
    private Integer year;

    @Column(name = "create_by", length = 100, nullable = false)
    private String createBy;

    @Column(name = "created_datetime", nullable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "last_updated_by", length = 100)
    private String lastUpdateBy;

    @Column(name = "last_updated_datetime")
    private LocalDateTime lastUpdatedBy;


}
