package com.tutorial.compositeprimarykey.embeddedid;

import com.tutorial.compositeprimarykey.embeddedid.dao.OrangTuaDao;
import com.tutorial.compositeprimarykey.embeddedid.entity.OrangTua;;
import com.tutorial.compositeprimarykey.entity.Anak;

import com.tutorial.config.HibernateConfiguration;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

public class EmbeddedIdTest {

    private final static Logger log = LoggerFactory.getLogger(EmbeddedIdTest.class);

    private Session session;

    private OrangTuaDao orangTuaDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.orangTuaDao = new OrangTuaDao(session); // masukan Session ke siswa DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    @Disabled
    void testGenerteObject() {
        this.session.beginTransaction();
    }

    @Test
//    @Disabled
    void testSaveEmbeddedId() {

//        Anak anak = Anak.builder()
//                .classId(UUID.randomUUID().toString())
//                .year(2002)
//                .build();

        OrangTua orangTua = OrangTua.builder()
                .id(Anak.builder()
                        .classId(UUID.randomUUID().toString())
                        .year(2011)
                        .build())
                .name("mumun")
                .asal("jakarta")
                .build();

        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
        orangTua = this.orangTuaDao.save(orangTua); // OrangTua save(OrangTua value)
        this.session.getTransaction().commit(); // void commit() // eksekusi
        log.info("Save Orang Tua: {}", orangTua);

        /**
         * Result hasil Embeddedable
         * 10:29:39.988 [main] INFO com.tutorial.compositeprimarykey.embeddedid.EmbeddedIdTest - Save Orang Tua: OrangTua(id=Anak(year=2011, classId=12e8499d-022e-46a8-a319-82efdb86c308), name=marimin, asal=semarang)
         * 10:29:39.971 [main] DEBUG org.hibernate.SQL -
         *     insert
         *     into
         *         kelas_embedded
         *         (asal, nama, class_id, tahun_lahir)
         *     values
         *         (?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         kelas_embedded
         *         (asal, nama, class_id, tahun_lahir)
         *     values
         *         (?, ?, ?, ?)
         */

    }

    @Test
    void findByIdEmbeddedId() {

        Anak anak = Anak.builder()
                .classId(UUID.randomUUID().toString())
                .year(2002)
                .build();

        OrangTua orangTua = OrangTua.builder()
                .id(anak)
                .name("mumun")
                .asal("jakarta")
                .build();


        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore

        orangTua = this.orangTuaDao.save(orangTua); // OrangTua save(OrangTua value)
        Optional<OrangTua> id = this.orangTuaDao.findById(anak); // Optional<OrangTua> findById(String value) // mendapatkan id dengan return optional<T>

        this.session.getTransaction().commit(); // void commit() // eksekusi

        // cek optional
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id.isPresent(), "object mahasiswa not null"); // boolean isPresent() // jika ada value return true, jika tidak ada value return false
        log.info("Save Anak: {}", anak);
        log.info("Save Orang Tua: {}", orangTua);
        log.info("Embedded FindById {} => {}", orangTua, id.orElse(null)); // T orElse(T other) // jika ada value return data nya, jika tidak ada kembalikan yang lain

        /**
         * result
         * 10:59:52.334 [main] INFO com.tutorial.compositeprimarykey.embeddedid.EmbeddedIdTest - Save Anak: Anak(year=2002, classId=a6b770f0-5727-4fcf-9878-c16de5b278b5)
         * 10:59:52.335 [main] INFO com.tutorial.compositeprimarykey.embeddedid.EmbeddedIdTest - Save Orang Tua: OrangTua(id=Anak(year=2002, classId=a6b770f0-5727-4fcf-9878-c16de5b278b5), name=mumun, asal=jakarta)
         * 10:59:52.336 [main] INFO com.tutorial.compositeprimarykey.embeddedid.EmbeddedIdTest - Embedded FindById OrangTua(id=Anak(year=2002, classId=a6b770f0-5727-4fcf-9878-c16de5b278b5), name=mumun, asal=jakarta) => OrangTua(id=Anak(year=2002, classId=a6b770f0-5727-4fcf-9878-c16de5b278b5), name=mumun, asal=jakarta)
         *
         */

    }

}
