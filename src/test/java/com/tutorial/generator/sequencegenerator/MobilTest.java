package com.tutorial.generator.genericgenerator.sequence;

import com.tutorial.generator.dao.MobilDao;
import com.tutorial.generator.entity.Mobil;
import com.tutorial.config.HibernateConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class MobilTest {

    private final static Logger log = LoggerFactory.getLogger(MobilTest.class);

    private Session session;
    private MobilDao mobilDao;

    @BeforeEach
    void setUp(){
        log.info("Memulai Object Mobil");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.mobilDao  = new MobilDao(session); // masukan Session ke mobil DAO
    }

    @AfterEach
    void tearDown(){
        log.info("Mengakhiri Object Mobil");
        this.session.close();
    }

    @Test
    @Disabled
    void testConnection(){

        Transaction transaction = this.session.beginTransaction();
        Assertions.assertNotNull(transaction.toString());
        Assertions.assertNotNull(transaction.getStatus());

    }

    @Test
    void testSaveMobil(){
        // init barang // id akan generate otomatis dengan Number
        Mobil mobil = Mobil.builder()
                .name("avanza")
                .merk("toyota")
                .type("sedan")
                .createBy("admin")
                .createdDateTime(LocalDateTime.now())
                .build();

        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore

        mobil = this.mobilDao.save(mobil); // Mobil save(Mobil value)

        log.info("Save Mobil: {}", mobil); // void commit() // eksekusi session

        this.session.getTransaction().commit();

        /**
         * result dari Generator Sequence, yang di lakukan oleh hibernate
         * nanti dia akan membuat @SequenceGenerator (sequenceName = "seq_kelas") akan di buatkan table sebagai counter auto increment di table
         * 08:41:17.452 [main] DEBUG org.hibernate.internal.util.EntityPrinter - com.tutorial.Generator.entity.Mobil{lastUpdatedBy=null, createBy=admin, merk=toyota, name=avanza, createdDateTime=2023-03-25T08:41:17.362347700, id=3, type=sedan, lastUpdateBy=null}
         * 08:41:17.400 [main] DEBUG org.hibernate.SQL -
         *     select
         *         next_val as id_val
         *     from
         *         seq_kelas for update
         *
         * Hibernate:
         *     select
         *         next_val as id_val
         *     from
         *         seq_kelas for update
         *
         * 08:41:17.404 [main] DEBUG org.hibernate.SQL -
         *     update
         *         seq_kelas
         *     set
         *         next_val= ?
         *     where
         *         next_val=?
         * Hibernate:
         *     update
         *         seq_kelas
         *     set
         *         next_val= ?
         *     where
         *         next_val=?
         */



    }



}
