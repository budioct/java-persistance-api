package com.tutorial.mapping.manytoone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity // identifier orm
@Table(name = "mahasiswa_manytoone", schema = "jointable") // modifikasi table
@GenericGenerator(name = "uuid_gen", strategy = "uuid2") // membuat autoincrement otomatis, dengan strategi uuid random
public class MahasiswaManyToOne {

    @Id // identifier orm
    @Column(name = "id") // modifikasi column
    @GeneratedValue(generator = "uuid_gen") // String generator() // harus sama dengan name @GenericGenerator
    private String id;

    @Column(name = "nim", length = 10)
    private Long nim;

    @Column(name = "nama", length = 40)
    private String nama;

    @Column(name = "tangga_lahir")
    private LocalDate tanggalLahir;

    @Column(name = "tahun_masuk", length = 4)
    private Integer tahunMasuk;

    @ManyToOne
    @JoinColumn(name = "kelas_id", nullable = false)
    private KelasManyToOne kelas; // satu kelas banyak mahasiswa // setiap mahasiswa hanya punya satu kelas

}
