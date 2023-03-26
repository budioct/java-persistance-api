package com.tutorial.mapping.kardinalitasonetoone;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.mapping.onetoone.dao.AlamatOneToOneDao;
import com.tutorial.mapping.onetoone.dao.MahasiswaOneToOneDao;
import com.tutorial.mapping.onetoone.entity.AlamatOneToOne;
import com.tutorial.mapping.onetoone.entity.MahasiswaOneToOne;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class MahasiswaOneToOneTest {

    private static final Logger log = LoggerFactory.getLogger(MahasiswaOneToOneTest.class);

    private Session session;
    private AlamatOneToOneDao alamatDao;
    private MahasiswaOneToOneDao mahasiswaDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.alamatDao = new AlamatOneToOneDao(session); // masukan Session ke mahasiswa DAO
        this.mahasiswaDao = new MahasiswaOneToOneDao(session); // masukan Session ke mahasiswa DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    void testSaveOnetoOneMapping(){
        // set init
        this.session.beginTransaction();

        AlamatOneToOne alamat = AlamatOneToOne.builder()
                .provinsi("jawa tengah")
                .kota("muntilan")
                .kelurahan("ale ale majenang")
                .kecamatan("majenang selatan")
                .rw("86")
                .rt("97")
                .kodePos(151516)
                .namaJalan("jl. perintis")
                .build();

        alamat = this.alamatDao.save(alamat);

        MahasiswaOneToOne mahasiswa = MahasiswaOneToOne.builder()
                .nim(1511510444L)
                .nama("ujang")
                .tanggalLahir(LocalDate.of(1998, 10, 22))
                .tahunMasuk(2017)
                .alamat(alamat)
                .build();

        mahasiswa =  this.mahasiswaDao.save(mahasiswa);
        this.session.getTransaction().commit();

        log.info("Save Alamat One To One= {}", alamat);
        log.info("Save Mahasiswa One To One= {}", mahasiswa);

        /**
         * hasil yang dibuatkan ORM akan ada acuan forenkey di table MahasiswaOneToOne
         * 22:45:26.850 [main] DEBUG org.hibernate.SQL -
         *
         *     alter table mahasiswa_onetoone
         *        add constraint FKkqtq37iclov0ceauq5eq9it60
         *        foreign key (alamat_id)
         *        references alamat_onetoone (id)
         *
         * hasil save:
         * 22:45:27.069 [main] INFO com.tutorial.mapping.kardinalitasonetoone.MahasiswaOneToOneTest - Save Alamat One To One= AlamatOneToOne(id=47c9c4fa-b091-41cf-a362-61722453a179, provinsi=jawa tengah, kota=muntilan, kelurahan=ale ale majenang, kecamatan=majenang selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. perintis)
         * 22:45:27.081 [main] INFO com.tutorial.mapping.kardinalitasonetoone.MahasiswaOneToOneTest - Save Mahasiswa One To One= MahasiswaOneToOne(id=05a349b8-5e6a-417d-aa51-fc86cd7d4dfb, nim=1511510444, nama=ujang, tanggalLahir=1998-10-22, tahunMasuk=2017, alamat=AlamatOneToOne(id=47c9c4fa-b091-41cf-a362-61722453a179, provinsi=jawa tengah, kota=muntilan, kelurahan=ale ale majenang, kecamatan=majenang selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. perintis))
         */


    }

}
