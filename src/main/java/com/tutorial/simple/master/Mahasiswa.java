package com.tutorial.simple.master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity // @Entity identifier wajib untuk class yang mau dibuat ORM ke DBMS
@Table(name = "mhs", schema = "simple") // @Table merubah nama dan schema // String schema() default "" // jika di postgre kita harus juga membuat schema pada DB nyaa
public class Mahasiswa {

    /**
     * ini adalah class entity, model, java bean, POJO, Domain
     * setelah class dibuat kita harus registry ke Hibernate, di file hibernate.cfg.xml. / java configuration harus lengkap dengan package2 nya
     */

    @Id // @Id identifier dan @entity wajib. dan menjadi primary key untuk table
    private Long id;

    @Column(name = "nim_mahasiswa", nullable = false, unique = true, length = 10)
    private String nim;

    @Column(name = "nama_mahasiswa", nullable = false, length = 25)
    private String nama;

    @Column(name = "tahun_masuk", length = 4)
    private Integer thnMasuk;

    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tglLahir;

    @Column(name = "create_at")
    private LocalDateTime create_at;

    @Column(name = "update_at")
    private LocalDateTime update_at;

    @Column(name = "is_active")
    private Boolean active;

    @Type(type = "text") // akan membuat type data kolom menjadi text
    @Column(name = "bio")
    private String biodata;

}
