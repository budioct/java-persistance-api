package com.tutorial.mapping.embedded;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.mapping.embbeded.dao.MahasiswaEmbeddedDao;
import com.tutorial.mapping.embbeded.entity.AlamatEmbeddedable;
import com.tutorial.mapping.embbeded.entity.MahasiswaEmbedded;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Optional;

public class MahasiswaEmbeddedTest {

    private static final Logger log = LoggerFactory.getLogger(MahasiswaEmbeddedTest.class);

    private Session session;

    private MahasiswaEmbeddedDao dao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.dao = new MahasiswaEmbeddedDao(session); // masukan Session ke mahasiswa DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    void testConnection() {
        this.session.beginTransaction();
    }

    @Test
    void testSaveMahasiswaEmbeddedMapping() {
        // init data
        MahasiswaEmbedded mahasiswa = MahasiswaEmbedded.builder()
                .nim(1511510444L)
                .nama("ujang")
                .tahunMasuk(2017)
                .tanggalLahir(LocalDate.of(1998, 10, 22))
                .alamat(AlamatEmbeddedable.builder()
                        .provinsi("jawa tengah")
                        .kota("muntilan")
                        .kelurahan("ale ale majenang")
                        .kecamatan("majenang selatan")
                        .rw("86")
                        .rt("97")
                        .kodePos(151516)
                        .namaJalan("jl. perintis")
                        .build())
                .build();

        this.session.beginTransaction();
        mahasiswa = this.dao.save(mahasiswa);
        this.session.getTransaction().commit();

        log.info("Save Mahasiswa Embedded: {}", mahasiswa);

        Optional<MahasiswaEmbedded> byId = this.dao.findById(mahasiswa.getId());
//        Optional<MahasiswaEmbedded> byId = this.dao.findById(3L);

        Assertions.assertTrue(byId.isPresent(), "Mahasiswa tidak ada"); // boolean isPresent()

        log.info("FindById MahasiswaEmbedded: {}", byId.get());

        /**
         * result save:
         * 16:59:02.561 [main] INFO com.tutorial.mapping.embedded.MahasiswaEmbeddedTest - Save Mahasiswa Embedded: MahasiswaEmbedded(id=6, nim=1511510012, nama=budhi, tanggalLahir=1996-10-22, tahunMasuk=2015, alamat=AlamatEmbeddedable(provinsi=jawa barat, kota=cideng, kelurahan=ale ale larangan, kecamatan=larangan utara, rw=76, rt=98, kodePos=151515, namaJalan=jl. perintis))
         * 17:02:20.166 [main] INFO com.tutorial.mapping.embedded.MahasiswaEmbeddedTest - Save Mahasiswa Embedded: MahasiswaEmbedded(id=7, nim=1511510444, nama=ujang, tanggalLahir=1998-10-22, tahunMasuk=2017, alamat=AlamatEmbeddedable(provinsi=jawa tengah, kota=muntilan, kelurahan=ale ale majenang, kecamatan=majenang selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. perintis))
         *
         * result findbyid:
         * 17:02:20.197 [main] INFO com.tutorial.mapping.embedded.MahasiswaEmbeddedTest - FindById MahasiswaEmbedded: MahasiswaEmbedded(id=7, nim=1511510444, nama=ujang, tanggalLahir=1998-10-22, tahunMasuk=2017, alamat=AlamatEmbeddedable(provinsi=jawa tengah, kota=muntilan, kelurahan=ale ale majenang, kecamatan=majenang selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. perintis))
         *
         */


    }


}
