package com.tutorial.mapping.onetomany.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity // identifier orm
@Table(name = "mahasiswa_onetomany", schema = "jointable") // modifikasi table
@GenericGenerator(name = "uuid_gen", strategy = "uuid2") // membuat autoincrement otomatis, dengan strategi uuid random
public class MahasiswaOneToMany {

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

    // jadi untuk menentukan one to many.. orm yang many kita buat dalam bentul List<T> atau Set<T> // array[]
    @OneToMany
    @JoinTable(name = "mahasiswa_onetomany_alamat_list",
            schema = "jointable",
            joinColumns = @JoinColumn(name = "mahasiswa_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "alamat_id", nullable = false),
            foreignKey = @ForeignKey(name = "fk_mahasiswa_id"),
            inverseForeignKey = @ForeignKey(name = "fk_alamat_id")
    )
    // joinColumns = @JoinColumn // membuat column di dalam entity class ini dengan modifikasi sendiri. untuk object MahasiswaOneToMany
    // inverseJoinColumns = @JoinColumn // membuat column di table sebrang nyaa dengan modifikasi sendiri. untuk object Collection<AlamatOneToMany>
    // foreignKey = @ForeignKey // kita bisa modifikasi foreign key. untuk object MahasiswaOneToMany
    // inverseForeignKey = @ForeignKey // kita bisa modifikasi foreign key. untuk object Collection<AlamatOneToMany>
    //@ToString.Exclude // supaya tidak error ketika ingin menampilkan method void toString()
    //@EqualsAndHashCode.Exclude // supaya tidak error ketika ingin menampilkan method void toString()
    //@FieldNameConstants.Exclude // supaya tidak error ketika ingin menampilkan method void toString()
    private List<AlamatOneToMany> listAlamat = new ArrayList<>();

}
