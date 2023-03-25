package com.tutorial.compositeprimarykey.idclass;

import com.tutorial.compositeprimarykey.IdClass.dao.MappingIdClassDao;
import com.tutorial.compositeprimarykey.IdClass.entity.KeyIdClass;
import com.tutorial.compositeprimarykey.IdClass.entity.MappingIdClass;
import com.tutorial.config.HibernateConfiguration;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class IdClassTest {

    private static final Logger log = LoggerFactory.getLogger(IdClassTest.class);

    private Session session;

    private MappingIdClassDao mappingIdClassDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.mappingIdClassDao = new MappingIdClassDao(session); // masukan Session ke siswa DAO
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
    void testSaveIdClass(){

        // init save()
        MappingIdClass data = MappingIdClass.builder()
                .year(2015)
                .classId("KBL-2")
                .name("TI - PBOL")
                .programStudy("Teknik Informatika")
                .description("belajar tentang programming object tingakt lanjut")
                .build();

        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
        data = this.mappingIdClassDao.save(data); // MappingIdClass save(MappingIdClass value)
        this.session.getTransaction().commit(); // void commit() // eksekusi
        log.info("Save Mapping Id Class: {}", data);

        // init findById
        Optional<MappingIdClass> byId = this.mappingIdClassDao.findById(KeyIdClass.builder()
                .year(2015)
                .classId("KBL-2")
                .build());
        Assertions.assertTrue(byId.isPresent(), "Mapping Id class findbyId is present"); // boolean isPresent() // cek apakah isi dari value ada. jika return bolean
        log.info("Find By Id Class: {}", byId);

        /**
         * result:
         * 14:52:38.463 [main] INFO com.tutorial.compositeprimarykey.idclass.IdClassTest - Save Mapping Id Class: MappingIdClass(year=2015, classId=KBL-2, name=TI - PBOL, programStudy=Teknik Informatika, description=belajar tentang programming object tingakt lanjut)
         * 14:52:38.478 [main] INFO com.tutorial.compositeprimarykey.idclass.IdClassTest - Find By Id Class: Optional[MappingIdClass(year=2015, classId=KBL-2, name=TI - PBOL, programStudy=Teknik Informatika, description=belajar tentang programming object tingakt lanjut)]
         */


    }


}
