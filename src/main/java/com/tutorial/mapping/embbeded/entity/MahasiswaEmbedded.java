package com.tutorial.mapping.embbeded.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity()
@Table(name = "mahasiswa_embeddes", schema = "embedded")
public class MahasiswaEmbedded {

    /**
     * @Embedded adalah untuk mereferensikan jenis yang dapat disematkan (mis. book.publisher).
     * digunakan untuk menentukan bahwa atribut entitas tertentu mewakili jenis yang dapat disematkan.
     */

    @Id
    @GeneratedValue
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "nim", length = 10)
    private Long nim;

    @Column(name = "nama", length = 40)
    private String nama;

    @Column(name = "tangga_lahir")
    private LocalDate tanggalLahir;

    @Column(name = "tahun_masuk", length = 4)
    private Integer tahunMasuk;

    @Embedded // object referensi  jenis yang dapat disematkan
    private AlamatEmbeddedable alamat;


}
