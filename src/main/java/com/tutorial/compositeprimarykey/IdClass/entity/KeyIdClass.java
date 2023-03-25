package com.tutorial.compositeprimarykey.IdClass.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class KeyIdClass implements Serializable {

    /**
     * kita akan implementasi Composite Primary Key, primary key multi dalam satu table
     * class ini wajib implements Serializable supaya bisa diterima oleh annotation @IdClass(KeyIdClass.class) di class Embedded nya
     * kita akan menggunakan @ClassId untuk implementasinya..
     */

    private Integer year;

    private String classId;

}
