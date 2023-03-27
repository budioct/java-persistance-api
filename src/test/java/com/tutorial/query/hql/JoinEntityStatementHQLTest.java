package com.tutorial.query.hql;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.mapping.onetoone.entity.MahasiswaOneToOne;
import com.tutorial.query.hql.dao.CreateUpdateDeleteStatementDao;
import com.tutorial.query.hql.dao.JoinEntityStatementDao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.List;

@Slf4j
public class JoinEntityStatementHQLTest {

    private Session session;

    private JoinEntityStatementDao dao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession();
        this.dao = new JoinEntityStatementDao(session);
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    @Disabled
    void testConnectionHibernate() {
        this.session.beginTransaction();
    }

    @Test
    void testFindByProvinsiJoinTableImplicit(){

        this.session.beginTransaction();
        var data = "jawa tengah";
        List<MahasiswaOneToOne> provinsi = this.dao.findByProvinsiWithImplicitJoin(data); // List<MahasiswaOneToOne> findByProvinsiWithImplicitJoin(String provinsi)

        Assertions.assertEquals(1, provinsi.size(), "tebakan salah bro!"); // tebak ada berapa data yang alamat provinsi jawa tengah di table mahasiswa

        this.session.getTransaction().commit();
        log.info("Find By Provinsi Join table: {}", provinsi);

        /**
         * result:
         * 00:10:31.118 [main] INFO com.tutorial.query.hql.JoinEntityStatementHQLTest - Find By Provinsi Join table: [MahasiswaOneToOne(id=05a349b8-5e6a-417d-aa51-fc86cd7d4dfb, nim=1511510444, nama=ujang, tanggalLahir=1998-10-22, tahunMasuk=2017, alamat=AlamatOneToOne(id=47c9c4fa-b091-41cf-a362-61722453a179, provinsi=jawa tengah, kota=muntilan, kelurahan=ale ale majenang, kecamatan=majenang selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. perintis))]
         */
    }

    @Test
    void testFindByProvinsiInnerJoinExplicit(){

        this.session.beginTransaction();
        var data = "jawa tengah";
        List<MahasiswaOneToOne> provinsi = this.dao.findByProvinsiWithExplicitJoinOn(data); // List<MahasiswaOneToOne> findByProvinsiWithExplicitJoinOn(String provinsi)

        Assertions.assertEquals(1, provinsi.size(), "tebakan salah bro!");

        this.session.getTransaction().commit();
        log.info("Find By Provinsi Inner Join: {}", provinsi);

        /**
         * result:
         * 00:36:14.769 [main] INFO com.tutorial.query.hql.JoinEntityStatementHQLTest - Find By Provinsi Inner Join: [MahasiswaOneToOne(id=05a349b8-5e6a-417d-aa51-fc86cd7d4dfb, nim=1511510444, nama=ujang, tanggalLahir=1998-10-22, tahunMasuk=2017, alamat=AlamatOneToOne(id=47c9c4fa-b091-41cf-a362-61722453a179, provinsi=jawa tengah, kota=muntilan, kelurahan=ale ale majenang, kecamatan=majenang selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. perintis))]
          */

    }






}
