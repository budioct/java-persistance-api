package com.tutorial.sequence;

import com.tutorial.Generator.dao.MobilDao;
import com.tutorial.Generator.entity.Mobil;
import com.tutorial.config.HibernateConfiguration;
import com.tutorial.simple.dao.MahasiswaDao;
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
        this.session = HibernateConfiguration.getSession();
        this.mobilDao  = new MobilDao(session);
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

        Mobil mobil = Mobil.builder()
                .name("avanza")
                .merk("toyota")
                .type("sedan")
                .createBy("admin")
                .createdDateTime(LocalDateTime.now())
                .build();

        this.session.beginTransaction();

        mobil = this.mobilDao.save(mobil);

        log.info("Save Mobil: {}", mobil);

        this.session.getTransaction().commit();

        /**
         * result dari Generator Sequence, yang di lakukan oleh hibernate
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
