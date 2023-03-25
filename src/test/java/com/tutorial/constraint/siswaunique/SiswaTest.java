package com.tutorial.constraint.siswaunique;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.constraint.dao.SiswaDao;
import com.tutorial.constraint.entity.Siswa;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class SiswaTest {

    private final static Logger log = LoggerFactory.getLogger(SiswaTest.class);

    private Session session;
    private SiswaDao siswaDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.siswaDao = new SiswaDao(session); // masukan Session ke siswa DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    void testConnectionHibernate(){
        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
    }

    @Test
    void testSavesiswaValid() {

        Siswa siswa = Siswa.builder()
                .id(UUID.randomUUID().toString())
                .name("nama-10")
                .year(2008)
                .createBy("admin")
                .createdDateTime(LocalDateTime.now())
                .build();

        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore

        siswa = this.siswaDao.save(siswa); //  Siswa save(Siswa value) // inject data siswa ke siswaDao

        this.session.getTransaction().commit(); // void commit() // eksekusi

        log.info("Siswa save: {}", siswa);

        /**
         * result:
         * 15:34:35.367 [main] DEBUG org.hibernate.SQL -
         *     insert
         *     into
         *         kelas_unique
         *         (create_by, created_datetime, last_updated_by, last_updated_datetime, nama, angkatan, id)
         *     values
         *         (?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         kelas_unique
         *         (create_by, created_datetime, last_updated_by, last_updated_datetime, nama, angkatan, id)
         *     values
         *         (?, ?, ?, ?, ?, ?, ?)
         */

    }

    @Test
    void testSavesiswaInvalid() {
        // data 1
        Siswa siswa = Siswa.builder()
                .id(UUID.randomUUID().toString())
                .name("name-1")
                .year(2008)
                .createBy("admin")
                .createdDateTime(LocalDateTime.now())
                .build();
        this.session.beginTransaction(); // // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
        Siswa simpan = this.siswaDao.save(siswa); //  Siswa save(Siswa value) // inject data siswa ke siswaDao
        this.session.getTransaction().commit(); // void commit() // eksekusi
        log.info("Siswa save: {}", simpan);

        // data 2
        siswa = Siswa.builder()
                .id(UUID.randomUUID().toString())
                .name("name-1")
                .year(2008)
                .createBy("admin")
                .createdDateTime(LocalDateTime.now())
                .build();
        this.session.beginTransaction(); // // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
        Siswa simpan1 = this.siswaDao.save(siswa); //  Siswa save(Siswa value) // inject data siswa ke siswaDao
        this.session.getTransaction().commit(); // void commit() // eksekusi
        log.info("Siswa save: {}", simpan1);

        /**
         * result Invalid, ketika column yang di insert datanya tidak boleh duplicate
         * Exception: Caused by: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'name-1-2008' for key 'kelas_unique.uq_kelas'
         */

    }


}
