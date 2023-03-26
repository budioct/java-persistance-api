package com.tutorial.mapping.manytomany.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "matakuliah_manytomamy", schema = "jointable")
@GenericGenerator(name = "uuid_gen", strategy = "uuid2")
public class MataKuliahManyToMany {

    @Id
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "id")
    private String id;

    @Column(name = "nama", length = 40)
    private String nama;

    @Column(name = "sks", length = 3)
    private Integer sks;

    // biasanya kita perlu yang beda dalam implementasi Many to Many.. kita akan gunakan Collection.Set<T>.. ketimbang Collection.List<T>
    @ManyToMany(mappedBy = "listMataKuliah") // karena kita tidak ingin menjadi 4 table.. kita ingin pakai mapping yang ada // kita tinggal String mappedBy() dari sebrang yang punya relasi M to M pada class ini
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    private Set<MahasiswaManyToMany> listMahasiswa = new HashSet<>();

}
