package com.tutorial.mapping.manytoone.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity // identifier orm
@Table(name = "kelas_manytoone", schema = "jointable") // modifikasi table
@GenericGenerator(name = "uuid_gen", strategy = "uuid2") // membuat autoincrement otomatis, dengan strategi uuid random
public class KelasManyToOne {

    @Id // identifier orm
    @Column(name = "id") // modifikasi column
    @GeneratedValue(generator = "uuid_gen") // String generator() // harus sama dengan name @GenericGenerator
    private String id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "angkata", length = 4)
    private Integer angkata;

    @Column(name = "program_studi")
    private String programStudi;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @OneToMany(mappedBy = "kelas") // String mappedBy() // supaya merujuk pada relasinya.. nama nya harus sama object reference pada List<MahasiswaManyToOne>..  @ManyToOn private KelasManyToOne kelas;
    private List<MahasiswaManyToOne> listMahasiswa = new ArrayList<>(); // satu kelas banyak mahasiswa // setiap mahasiswa hanya punya satu kelas


}
