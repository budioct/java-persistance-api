package com.tutorial.query.hql;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.query.hql.dao.CreateUpdateDeleteStatementDao;
import com.tutorial.simple.master.Mahasiswa;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class CreateUpdateDeleteStatementHQLTest {


    private Session session;

    private CreateUpdateDeleteStatementDao dao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession();
        this.dao = new CreateUpdateDeleteStatementDao(session);
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
    void testCreateDataHQL() {

        this.session.beginTransaction();

        Mahasiswa naysila = Mahasiswa.builder()
                .id(4L)
                .nama("naysila")
                .nim("1511510088")
                .tglLahir(LocalDate.now())
                .active(false)
                .thnMasuk(2015)
                .create_at(LocalDateTime.now())
                .build();

        naysila = this.dao.save(naysila);
        Assertions.assertNotNull(naysila);

        this.session.getTransaction().commit();

        log.info("Save Data: {}", naysila);

        /**
         * result:
         * 11:21:35.435 [main] INFO com.tutorial.query.hql.CreateUpdateDeleteStatementHQLTest - Save Data: Mahasiswa(id=4, nim=1511510088, nama=naysila, thnMasuk=2015, tglLahir=2023-03-27, create_at=2023-03-27T11:21:35.383125600, update_at=null, active=false, biodata=null)
         */

    }

    @Test
    void testUpdateDataHQL() {

        this.session.beginTransaction();

        var tahunmasuk = 2015;
        Mahasiswa ubahData = Mahasiswa.builder()
                .id(4L)
                .nama("kodok lompat")
                .thnMasuk(2015)
                .active(true)
                .biodata("aku suka shooping brooo")
                .build();

        ubahData = this.dao.update(ubahData);

        Assertions.assertNotNull(ubahData);
        Assertions.assertEquals(tahunmasuk, ubahData.getThnMasuk());

        this.session.getTransaction().commit();

        log.info("Update Data: {}", ubahData);

        /**
         * Result:
         * 11:38:28.598 [main] INFO com.tutorial.query.hql.dao.CreateUpdateDeleteStatementDao - updatate rows in mahasiswa= 1
         * 11:38:28.609 [main] INFO com.tutorial.query.hql.CreateUpdateDeleteStatementHQLTest - Update Data: Mahasiswa(id=4, nim=null, nama=kodok lompat, thnMasuk=2015, tglLahir=null, create_at=null, update_at=null, active=true, biodata=aku suka shooping brooo)
         */

    }

    @Test
    void testRemoveHQL(){

        this.session.beginTransaction();

        var id = 4L;
        boolean hasil = this.dao.removeById(id);

        Assertions.assertNotNull(hasil);
        Assertions.assertTrue(hasil);

        this.session.getTransaction().commit();

        log.info("Remove Data: {}", hasil);

        /**
         * Result:
         * 11:43:01.218 [main] INFO com.tutorial.query.hql.CreateUpdateDeleteStatementHQLTest - Remove Data: true
         */

    }

    @Test
    void testFindById(){

        this.session.beginTransaction();

        var id = 2L;

        Optional<Mahasiswa> byId = this.dao.findById(id);
        Mahasiswa mahasiswa = byId.get();

        Assertions.assertNotNull(mahasiswa);
        Assertions.assertTrue(byId.isPresent(), "Data Tidak Ada!");
        Assertions.assertEquals(id, mahasiswa.getId());
        Assertions.assertSame(id, mahasiswa.getId());

        this.session.getTransaction().commit();

        log.info("Find ID: {}", byId.get().getId());
        log.info("Find Data: {}", byId.get());

        /**
         * result:
         * 11:48:39.857 [main] INFO com.tutorial.query.hql.CreateUpdateDeleteStatementHQLTest - Find ID: 2
         * 11:48:39.858 [main] INFO com.tutorial.query.hql.CreateUpdateDeleteStatementHQLTest - Find Data: Mahasiswa(id=2, nim=1511510104, nama=agung rianto (Updated), thnMasuk=2015, tglLahir=1996-03-22, create_at=2023-03-24T11:42:00.865907, update_at=2023-03-24T11:42:00.865907, active=false, biodata=null)
         */

    }

    @Test
    void testSaveHQL(){

        this.session.beginTransaction();

        Mahasiswa simpanData = Mahasiswa.builder()
                .id(5L)
                .nim("1611610089")
                .tglLahir(LocalDate.of(1990, 10, 2))
                .nama("kodok lompat")
                .thnMasuk(2015)
                .create_at(LocalDateTime.now())
                .active(true)
                .biodata("aku suka shooping brooo")
                .build();

        simpanData = this.dao.save(simpanData);

        Assertions.assertNotNull(simpanData);
        Assertions.assertTrue(simpanData.equals(simpanData), "data tidak ada");

        this.session.getTransaction().commit();

        log.info("Save Data: {}", simpanData);

        /**
         * result:
         * 11:56:45.019 [main] INFO com.tutorial.query.hql.CreateUpdateDeleteStatementHQLTest - Save Data: Mahasiswa(id=5, nim=1611610089, nama=kodok lompat, thnMasuk=2015, tglLahir=1990-10-02, create_at=2023-03-27T11:56:44.970880500, update_at=null, active=true, biodata=aku suka shooping brooo)
         */



    }




}
