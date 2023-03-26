package com.tutorial.mapping.onetomany.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity // identifier orm
@Table(name = "alamat_onetomany", schema = "jointable") // modifikasi table
@GenericGenerator(name = "uuid_gen", strategy = "uuid2") // membuat autoincrement otomatis, dengan strategi uuid random
public class AlamatOneToMany {

    @Id // identifier orm
    @Column(name = "id") // mofifikasi column table
    @GeneratedValue(generator = "uuid_gen") // String generator() // harus sama dengan name @GenericGenerator
    private String id;

    @Column(name = "provinsi", length = 50)
    private String provinsi;

    @Column(name = "kota", length = 50)
    private String kota;

    @Column(name = "kelurahan", length = 100)
    private String kelurahan;

    @Column(name = "kecamatan", length = 100)
    private String kecamatan;

    @Column(name = "rw", length = 3)
    private String rw;

    @Column(name = "rt", length = 3)
    private String rt;

    @Column(name = "kode_pos", length = 6)
    private Integer kodePos;

    @Column(name = "nama_jalan", length = 100)
    private String namaJalan;

}
