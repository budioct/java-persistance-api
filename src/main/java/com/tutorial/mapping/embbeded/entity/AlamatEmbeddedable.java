package com.tutorial.mapping.embbeded.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Embeddable // menentukan jenis yang dapat di sematkan di object lain (asosiatif)
public class AlamatEmbeddedable {

    /**
     * @Embeddable digunakan untuk mendeskripsikan jenis pemetaan itu sendiri (mis. Penerbit).
     * digunakan untuk menentukan jenis yang dapat disematkan. Seperti tipe dasar, tipe yang dapat disematkan tidak memiliki identitas apa pun, dikelola oleh entitas pemiliknya.
     *
     * @Embedded adalah untuk mereferensikan jenis yang dapat disematkan (mis. book.publisher).
     * digunakan untuk menentukan bahwa atribut entitas tertentu mewakili jenis yang dapat disematkan.
     */

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
