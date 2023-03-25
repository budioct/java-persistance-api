package com.tutorial.mapping.onetoone.entity;

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
@Entity
@Table(name = "mahasiswa_onetoone", schema = "jointable")
@GenericGenerator(name = "uuid_gen", strategy = "uuid2")
public class MahasiswaOneToOne {

    @Id
    @Column(name = "id")
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

    // Asosiasi
    @OneToOne // kardinalitas one to one..
    @JoinColumn(name = "alamat_id", nullable = false, unique = true) // modifikasi primary key reference table mahasiswa
    // nullable = false, unique = true // jadi 1 alamat tidak boleh orang dipakai orang lain, kenapa? supaya kita bisa make sure
    // nullable = true, unique = false // kalau alamat boleh null kolom jangan di kasih unique, kenapa? supaya null itu tidak bisa banyak
    // secara contraint dbms itu bener, 1 alamat hanya boleh digunakan 1 mahasiswa
    private AlamatOneToOne alamat;

}
