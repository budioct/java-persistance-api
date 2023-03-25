package com.tutorial.compositeprimarykey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable // @Embeddable agar bisa di terima sebagai Composite primary key di class yang menjadi entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Anak implements Serializable {

    /**
     * @Embeddable // @Embeddable agar bisa di terima sebagai Composite primary key di class yang menjadi entity
     * wajib implements Serializable supaya bisa di sum di table
     * class Anak ini akan di Embeddable ke class OrangTua..
     * jadi tujuanya class ini dibuat adalah sebagai primary key. tetatpi di eksekusi di class OrangTua
     */

    @Column(name = "tahun_lahir", length = 4) // jika ingin menambahkan constraint pada kolom.. columnDefinition = "int check(tahun_lahir => 1990)"
    private Integer year;
    @Column(name = "class_id")
    private String classId;

}
