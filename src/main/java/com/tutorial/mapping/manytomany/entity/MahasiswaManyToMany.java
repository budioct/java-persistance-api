package com.tutorial.mapping.manytomany.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "mahasiswa_manytomamy", schema = "jointable")
@GenericGenerator(name = "uuid_gen", strategy = "uuid2")
public class MahasiswaManyToMany {

    @Id
    @GeneratedValue(generator = "uuid_gen")
    private String id;

    @Column(name = "nim", length = 10)
    private Long nim;

    @Column(name = "nama", length = 40)
    private String nama;

    @Column(name = "tangga_lahir")
    private LocalDate tanggalLahir;

    @Column(name = "tahun_masuk", length = 4)
    private Integer tahunMasuk;

    // biasanya kita perlu yang beda dalam implementasi Many to Many.. kita akan gunakan Collection.Set<T>.. ketimbang Collection.List<T>
    @ManyToMany
    @JoinTable(name = "mahasiswa_ambil_matakuliah",
            schema = "jointable",
            joinColumns = @JoinColumn(name = "mahasiswa_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "matakuliah_id", nullable = false),
            foreignKey = @ForeignKey(name = "fk_mahasiswa_kuliah_mahasiswa_id"),
            inverseForeignKey = @ForeignKey(name = "fk_mahasiswa_kuliah_matakuliah_id")
    )
    // jika kita tidak Exlude makan akan kena Error memory heap , karena circular dependency, sama sama di panggil terus sama hibernate karena object yang berelasi
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    private Set<MataKuliahManyToMany> listMataKuliah = new HashSet<>();


}
