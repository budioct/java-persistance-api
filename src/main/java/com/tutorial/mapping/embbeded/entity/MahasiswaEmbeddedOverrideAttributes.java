package com.tutorial.mapping.embbeded.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "mahasiswa_override_att", schema = "embedded")
public class MahasiswaEmbeddedOverrideAttributes {

    /**
     * akan membuat Model dari class @Embeddedable tetapi dengan property yang berbeda. tetatpi dari referensi class @Embeddedable
     * untuk melakukanya kita mengunakan Annotation
     *
     * @AttributeOverrides( value = {
     * @AttributeOveride( namae = "provinsi" -> attribute dari @Embeddeaable yang mau di rubah)
     * colomn = @Column(name = "kita bisa modifikasi di sini")
     * }
     * )
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

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "provinsi", column = @Column(name = "rumah_provinsi", length = 50, nullable = false)),
            @AttributeOverride(name = "kota", column = @Column(name = "rumah_kota", length = 50, nullable = false)),
            @AttributeOverride(name = "kelurahan", column = @Column(name = "rumah_kelurahan", length = 50, nullable = false)),
            @AttributeOverride(name = "kecamatan", column = @Column(name = "rumah_kecamatan", length = 50, nullable = false)),
            @AttributeOverride(name = "rw", column = @Column(name = "rumah_rw", length = 50, nullable = false)),
            @AttributeOverride(name = "rt", column = @Column(name = "rumah_rt", length = 50, nullable = false)),
            @AttributeOverride(name = "kodePos", column = @Column(name = "rumah_kode_pos", length = 50, nullable = false)),
            @AttributeOverride(name = "namaJalan", column = @Column(name = "rumah_nama_jalan", length = 50, nullable = false)),
    })
    private AlamatEmbeddedable alamatRumah; // object referensi  jenis yang dapat disematkan di @Embeddedable. otomatis field nya akan menjadi kolom di class ini

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "provinsi", column = @Column(name = "ortu_provinsi", length = 50)),
            @AttributeOverride(name = "kota", column = @Column(name = "ortu_kota", length = 50)),
            @AttributeOverride(name = "kelurahan", column = @Column(name = "ortu_kelurahan", length = 50)),
            @AttributeOverride(name = "kecamatan", column = @Column(name = "ortu_kecamatan", length = 50)),
            @AttributeOverride(name = "rw", column = @Column(name = "ortu_rw", length = 50)),
            @AttributeOverride(name = "rt", column = @Column(name = "ortu_rt", length = 50)),
            @AttributeOverride(name = "kodePos", column = @Column(name = "ortu_kode_pos", length = 50)),
            @AttributeOverride(name = "namaJalan", column = @Column(name = "ortu_nama_jalan", length = 50)),
    }) // boleh null column alamt orang tua
    private AlamatEmbeddedable alamatOrangTua; // object referensi  jenis yang dapat disematkan di @Embeddedable . otomatis field nya akan menjadi kolom di class ini

}
