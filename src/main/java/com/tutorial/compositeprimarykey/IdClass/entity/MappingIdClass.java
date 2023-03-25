package com.tutorial.compositeprimarykey.IdClass.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@IdClass(KeyIdClass.class) // Class value(); // mirip dengan Class<T> akan membaca seluruh detail class tersebut.. ini adalah class yang akan menjadi composite embeded primaryKey dengan metode IdClass
@Table(name = "kelas_idclass", schema = "idclass")
public class MappingIdClass {

    @Id
    @Column(name = "tahun_angkatan", length = 4, columnDefinition = "int check(tahun_angkatan >= 1990)")
    private Integer year;
    
    @Column(name = "class_id", length = 50)
    private String classId;

    @Column(name = "name")
    private String name;

    @Column(name = "prodi")
    private String programStudy;

    @Column(name = "description")
    private String description;

}
