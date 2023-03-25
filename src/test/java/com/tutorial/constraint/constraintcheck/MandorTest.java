package com.tutorial.constraint.constraintcheck;

import com.tutorial.config.HibernateConfiguration;

import com.tutorial.constraint.dao.MandorDao;
import com.tutorial.constraint.entity.Mandor;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class MandorTest {

    private final static Logger log = LoggerFactory.getLogger(MandorTest.class);

    private Session session;

    private MandorDao mandorDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.mandorDao = new MandorDao(session); // masukan Session ke siswa DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    void testSaveMandorValid() {

        Mandor mandor = Mandor.builder()
                .id(UUID.randomUUID().toString())
                .name("nama-12")
                .usia("20")
                .gaji(new BigDecimal(2_100_000))
                .createBy("admin")
                .createdDateTime(LocalDateTime.now())
                .build();

        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
        mandor = this.mandorDao.save(mandor); //  Mandor save(Mador value) // inject data mandor ke mandorDao
        this.session.getTransaction().commit(); // void commit() // eksekusi

        log.info("Mandor save: {}", mandor);

        /**
         * result
         * 16:17:16.651 [main] INFO com.tutorial.constraint.mandorCheck.MandorTest - Mandor save: Mandor(id=ac520cf1-cd7e-4c1d-9401-13ca6ec08472, name=nama-12, usia=20, gaji=2100000, createBy=admin, createdDateTime=2023-03-24T16:17:16.588629200, lastUpdateBy=null, lastUpdatedBy=null)
         * 16:17:16.622 [main] DEBUG org.hibernate.SQL -
         *     insert
         *     into
         *         kelas_check
         *         (create_by, created_datetime, gaji, last_updated_by, last_updated_datetime, nama, usia, id)
         *     values
         *         (?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         kelas_check
         *         (create_by, created_datetime, gaji, last_updated_by, last_updated_datetime, nama, usia, id)
         *     values
         *         (?, ?, ?, ?, ?, ?, ?, ?)
         */

    }

    @Test
    void testSaveMandorInvalid() {

        Mandor mandor = Mandor.builder()
                .id(UUID.randomUUID().toString())
                .name("nama-10")
                .usia("21")
                .gaji(new BigDecimal(1_500_000)) // gaji di bawah permintaan constraint
                .createBy("admin")
                .createdDateTime(LocalDateTime.now())
                .build();

        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
        mandor = this.mandorDao.save(mandor); //  Mandor save(Mador value) // inject data mandor ke mandorDao
        this.session.getTransaction().commit(); // void commit() // eksekusi
        log.info("Mandor save: {}", mandor);

        /**
         * result:
         * Exception
         * javax.persistence.PersistenceException: org.hibernate.exception.GenericJDBCException: could not execute statement
         * java.sql.SQLException: Check constraint 'kelas_check_chk_1' is violated.
         * Caused by: java.sql.SQLException: Check constraint 'kelas_check_chk_1' is violated.
         * 16:12:45.420 [main] ERROR org.hibernate.engine.jdbc.spi.SqlExceptionHelper - Check constraint 'kelas_check_chk_1' is violated.
         */

    }


}
