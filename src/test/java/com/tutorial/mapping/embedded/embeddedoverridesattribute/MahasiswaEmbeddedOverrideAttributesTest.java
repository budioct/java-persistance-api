package com.tutorial.mapping.embedded.embeddedoverridesattribute;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.mapping.embbeded.dao.MahasiswaEmbeddedDao;
import com.tutorial.mapping.embbeded.dao.MahasiswaEmbeddedOverrideAttributesDao;
import com.tutorial.mapping.embbeded.entity.AlamatEmbeddedable;
import com.tutorial.mapping.embbeded.entity.MahasiswaEmbedded;
import com.tutorial.mapping.embbeded.entity.MahasiswaEmbeddedOverrideAttributes;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class MahasiswaEmbeddedOverrideAttributesTest {

    private static final Logger log = LoggerFactory.getLogger(MahasiswaEmbeddedOverrideAttributesTest.class);

    private Session session;
    private MahasiswaEmbeddedOverrideAttributesDao dao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.dao = new MahasiswaEmbeddedOverrideAttributesDao(session); // masukan Session ke mahasiswa DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    @Disabled
    void testConnection() {
        this.session.beginTransaction();
    }

    @Test
    void testSaveEmbeddedOverrideAttributeValid() {

        // init data
        MahasiswaEmbeddedOverrideAttributes mahasiswa = MahasiswaEmbeddedOverrideAttributes.builder()
                .nim(1511510444L)
                .nama("ujang")
                .tanggalLahir(LocalDate.of(1998, 10, 22))
                .tahunMasuk(2017)
                .alamatRumah(AlamatEmbeddedable.builder()
                        .provinsi("jawa tengah")
                        .kota("muntilan")
                        .kelurahan("ale ale majenang")
                        .kecamatan("majenang selatan")
                        .rw("86")
                        .rt("97")
                        .kodePos(151516)
                        .namaJalan("jl. perintis")
                        .build())
                .alamatOrangTua(AlamatEmbeddedable.builder()
                        .provinsi("jawa tengah")
                        .kota("muntilan")
                        .kelurahan("ale ale bumi ayu")
                        .kecamatan("bumi ayu selatan")
                        .rw("66")
                        .rt("77")
                        .kodePos(151212)
                        .namaJalan("jl. bangsa 12")
                        .build())
                .build();

        this.session.beginTransaction();
        mahasiswa = this.dao.save(mahasiswa);
        this.session.getTransaction().commit();

        log.info("Save Mahasiswa Embedded: {}", mahasiswa);

        /**
         * result save:
         * 21:04:08.233 [main] INFO com.tutorial.mapping.embedded.embeddedoverridesattribute.MahasiswaEmbeddedOverrideAttributesTest - Save Mahasiswa Embedded: MahasiswaEmbeddedOverrideAttributes(id=9, nim=1511510444, nama=ujang, tanggalLahir=1998-10-22, tahunMasuk=2017, alamatRumah=AlamatEmbeddedable(provinsi=jawa tengah, kota=muntilan, kelurahan=ale ale majenang, kecamatan=majenang selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. perintis), alamatOrangTua=null)
         * 21:08:07.822 [main] INFO com.tutorial.mapping.embedded.embeddedoverridesattribute.MahasiswaEmbeddedOverrideAttributesTest - Save Mahasiswa Embedded: MahasiswaEmbeddedOverrideAttributes(id=11, nim=1511510444, nama=ujang, tanggalLahir=1998-10-22, tahunMasuk=2017, alamatRumah=AlamatEmbeddedable(provinsi=jawa tengah, kota=muntilan, kelurahan=ale ale majenang, kecamatan=majenang selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. perintis), alamatOrangTua=AlamatEmbeddedable(provinsi=jawa tengah, kota=muntilan, kelurahan=ale ale bumi ayu, kecamatan=bumi ayu selatan, rw=66, rt=77, kodePos=151212, namaJalan=jl. bangsa 12))
         */

    }

    @Test
    void testSaveEmbeddedOverrideAttributeInvalid() {

        // init data
        MahasiswaEmbeddedOverrideAttributes mahasiswa = MahasiswaEmbeddedOverrideAttributes.builder()
                .nim(1511510444L)
                .nama("ujang")
                .tanggalLahir(LocalDate.of(1998, 10, 22))
                .tahunMasuk(2017)
                .alamatRumah(null)
                .alamatOrangTua(AlamatEmbeddedable.builder()
                        .provinsi("jawa tengah")
                        .kota("muntilan")
                        .kelurahan("ale ale bumi ayu")
                        .kecamatan("bumi ayu selatan")
                        .rw("66")
                        .rt("77")
                        .kodePos(151212)
                        .namaJalan("jl. bangsa 12")
                        .build())
                .build();

        this.session.beginTransaction();
        mahasiswa = this.dao.save(mahasiswa);
        this.session.getTransaction().commit();

        log.info("Save Mahasiswa Embedded: {}", mahasiswa);

        /**
         * result save:
         *  Caused by: java.sql.SQLIntegrityConstraintViolationException: Column 'rumah_kecamatan' cannot be null
         *  karena sudah di set embeddedattribute dari alamat ruah tidak boleh null
         */

    }


}
