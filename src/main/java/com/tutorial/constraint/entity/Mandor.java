package com.tutorial.constraint.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(
        name = "kelas_check",
        schema = "bagunan"
)
public class Mandor {

    /**
     * Di class ini kita akan membuat Check Constraint
     * <p>
     * ini adalah class entity, model, java bean, POJO, Domain
     * setelah class dibuat kita harus registry ke Hibernate, di file hibernate.cfg.xml. / java configuration harus lengkap dengan package2 nya
     */

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nama", nullable = false)
    private String name;

    @Column(name = "usia", length = 3)
    private String usia;

    @Column(name = "gaji", columnDefinition = "decimal check(gaji >= 2000000)")
    private BigDecimal gaji;

    @Column(name = "create_by", length = 100, nullable = false)
    private String createBy;

    @Column(name = "created_datetime", nullable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "last_updated_by", length = 100)
    private String lastUpdateBy;

    @Column(name = "last_updated_datetime")
    private LocalDateTime lastUpdatedBy;

}
