package com.tutorial.compositeprimarykey.embeddedid.entity;

import com.tutorial.compositeprimarykey.entity.Anak;
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
@Table(name = "kelas_embedded", schema = "embeddable")
public class OrangTua {

    /**
     * class OrangTua  ini akan di EmbeddedId dari class Anak
     * <p>
     * membuat primary 2 di satu table seperti dibawah di compiler bisa, ketika di running error
     *
     * @Id private String id1;
     * @Id private String id2;
     */

    @EmbeddedId // @EmbeddedId -> class anak yang sudah di @Embeddable dengan 2 primary key.
    private Anak id;
    @Column(name = "nama")
    private String name;
    @Column(name = "asal")
    private String asal;

}
