package com.tutorial.query.hql;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.mapping.enumeration.entity.EmployeeStatus;
import com.tutorial.mapping.manytoone.entity.KelasManyToOne;
import com.tutorial.query.hql.dao.DistinctStatementDao;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DistinctStatementHQLTest {

    private static final Logger log = LoggerFactory.getLogger(DistinctStatementHQLTest.class);

    private Session session;
    private DistinctStatementDao dao;

    @BeforeEach
    void setUp(){
        log.info("Start hibernate session Broo!");
        this.session = HibernateConfiguration.getSession(); // memulai koneksi
        this.dao = new DistinctStatementDao(session);
    }

    @AfterEach
    void tearDown(){
        log.info("destroy hibernate session!");
        this.session.close(); // void close() // Akhiri sesi dengan melepaskan koneksi JDBC dan bersihkan.
    }

    @Test
    @Disabled
    void testConnection(){

        this.session.beginTransaction(); // Transaction beginTransaction() // Mulai unit kerja dan kembalikan objek Transaksi terkait.

    }

    @Test
    void testGetStatusEmployee(){

        this.session.beginTransaction();

        EmployeeStatus active = EmployeeStatus.ACTIVE;

        List<EmployeeStatus> distictProjectSQL = this.dao.findByDistictProjectSQL();

        Assertions.assertEquals(3, distictProjectSQL.size(), "tebakan salah bro");

        // Transaction getTransaction() //Dapatkan instance Transaksi yang terkait dengan sesi ini.
        // void commit( Komit transaksi sumber daya saat ini, tulis perubahan apa pun yang belum dihapus ke database.
        this.session.getTransaction().commit();

        log.info("Result find distict project SQL: {}", distictProjectSQL);

        /**
         * result:
         * 08:36:01.111 [main] INFO com.tutorial.query.hql.DistinctStatementHQLTest - Result find distict project SQL: [LEAVE, ACTIVE]
         * 08:37:50.084 [main] INFO com.tutorial.query.hql.DistinctStatementHQLTest - Result find distict project SQL: [LEAVE, ACTIVE, RESIGN]
         */

    }

    @Test
    void testGetKelasMahasiswa(){

        this.session.beginTransaction();

        List<KelasManyToOne> entityQuery = this.dao.findByDistictEntityQuery();

        Assertions.assertEquals(4, entityQuery.size(), "tebakan salah bro");

        // Transaction getTransaction() //Dapatkan instance Transaksi yang terkait dengan sesi ini.
        // void commit( Komit transaksi sumber daya saat ini, tulis perubahan apa pun yang belum dihapus ke database.
        this.session.getTransaction().commit();

        log.info("Result find distict entity query : {}", entityQuery);

        /**
         * result:
         * 08:54:00.469 [main] INFO com.tutorial.query.hql.DistinctStatementHQLTest - Result find distict entity query : [KelasManyToOne(id=0bff1091-cafd-4b06-bd24-94e792583090, nama=Pemograman Berbasis Object Lanjutan, angkata=2016, programStudi=Teknik Informatika), KelasManyToOne(id=3135e7d2-d74a-47cf-a2b9-704a696bd356, nama=Pendidikan Pancasila, angkata=2016, programStudi=Teknik Informatika), KelasManyToOne(id=3135e7d2-d74a-47cf-a2b9-704a696bd856, nama=Perancangan Basis Data, angkata=2016, programStudi=Teknik Informatika)]
         * 08:58:01.919 [main] INFO com.tutorial.query.hql.DistinctStatementHQLTest - Result find distict entity query : [KelasManyToOne(id=0bff1091-cafd-4b06-bd24-94e792583090, nama=Pemograman Berbasis Object Lanjutan, angkata=2016, programStudi=Teknik Informatika), KelasManyToOne(id=3135e7d2-d74a-47cf-a2b9-704a696bd123, nama=Bangunan, angkata=2017, programStudi=Teknik Informatika), KelasManyToOne(id=3135e7d2-d74a-47cf-a2b9-704a696bd356, nama=Pendidikan Pancasila, angkata=2016, programStudi=Teknik Informatika), KelasManyToOne(id=3135e7d2-d74a-47cf-a2b9-704a696bd856, nama=Perancangan Basis Data, angkata=2016, programStudi=Teknik Informatika)]
         *
         */

    }



















}
