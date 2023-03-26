package com.tutorial.query.hql;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.query.hql.dao.SelectStatementHQLDao;
import com.tutorial.simple.master.Mahasiswa;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class SelectStatementHQLTest {

    private final static Logger log = LoggerFactory.getLogger(SelectStatementHQLTest.class);

    private Session session;

    private SelectStatementHQLDao statementDao;


    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession();
        this.statementDao = new SelectStatementHQLDao(session);
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
    void testSelectStaementHQLFindAll() {

        List<Mahasiswa> mahasiswaList = this.statementDao.findAll();

        Assertions.assertEquals(2, mahasiswaList.size(), "jumlah data");
        log.info("data: {}", mahasiswaList);

        /**
         * hasil:
         * 18:25:42.143 [main] DEBUG org.hibernate.hql.internal.ast.ErrorTracker - throwQueryException() : no errors
         * 18:25:42.143 [main] DEBUG org.hibernate.hql.internal.ast.QueryTranslatorImpl - HQL: from  com.tutorial.simple.master.Mahasiswa where id = ?1
         * 18:25:42.143 [main] INFO com.tutorial.query.hql.SelectStatementHQLTest - data: [Mahasiswa(id=2, nim=1511510104, nama=agung rianto (Updated), thnMasuk=2015, tglLahir=1996-03-22, create_at=2023-03-24T11:42:00.865907, update_at=2023-03-24T11:42:00.865907, active=false, biodata=null), Mahasiswa(id=3, nim=1511510105, nama=fahmi, thnMasuk=2015, tglLahir=1997-03-22, create_at=2023-03-24T15:01:04.996546, update_at=2023-03-24T15:01:04.996546, active=false, biodata=null)]
         */

    }

    @Test
    void testSelectStaementHQLFindById() {

        var data1 = 3L;
        var data2 = 6L;

        Optional<Mahasiswa> byId1 = this.statementDao.findById(data1);
        Optional<Mahasiswa> byId2 = this.statementDao.findById(data2);

        Assertions.assertTrue(byId1.isPresent(), "Id tidak ada"); // void assertTrue(boolean condition, String message)
        Assertions.assertEquals(data1, byId1.get().getId()); // void assertEquals(long expected, Long actual)
        Assertions.assertSame(data1, byId1.get().getId()); // void assertSame(Object expected, Object actual)

        log.info("Find ById 1 = {}", byId1.get()); // T get()
        log.info("Find ById 1 = {}", byId1.orElse(null)); // T orElse(T other)

        Assertions.assertFalse(byId2.isPresent(), "Id Ada"); // T get()
        log.info("Find ById 2 = {}", byId2.orElse(null)); // T orElse(T other)

        /**
         * hasil:
         * 18:25:42.143 [main] DEBUG org.hibernate.hql.internal.ast.ErrorTracker - throwQueryException() : no errors
         * 18:25:42.143 [main] DEBUG org.hibernate.hql.internal.ast.QueryTranslatorImpl - SQL: select mahasiswa0_.id as id1_19_, mahasiswa0_.is_active as is_activ2_19_, mahasiswa0_.bio as bio3_19_, mahasiswa0_.create_at as create_a4_19_, mahasiswa0_.nama_mahasiswa as nama_mah5_19_, mahasiswa0_.nim_mahasiswa as nim_maha6_19_, mahasiswa0_.tanggal_lahir as tanggal_7_19_, mahasiswa0_.tahun_masuk as tahun_ma8_19_, mahasiswa0_.update_at as update_a9_19_ from mhs mahasiswa0_ where mahasiswa0_.id=?
         * 18:25:42.143 [main] INFO com.tutorial.query.hql.SelectStatementHQLTest - Find ById 1 = Mahasiswa(id=3, nim=1511510105, nama=fahmi, thnMasuk=2015, tglLahir=1997-03-22, create_at=2023-03-24T15:01:04.996546, update_at=2023-03-24T15:01:04.996546, active=false, biodata=null)
         * 18:37:42.013 [main] INFO com.tutorial.query.hql.SelectStatementHQLTest - Find ById 2 = null
         */
    }

    @Test
    void testFindByNim() {

        var data = "1511510105";
        Optional<Mahasiswa> mahasiswa = this.statementDao.findByNim(data);

        Assertions.assertTrue(mahasiswa.isPresent(), "data tidak ada");
        Assertions.assertEquals(data, mahasiswa.get().getNim());

        log.info("Find By Nim: {}", mahasiswa.get().getNim());
        log.info("Find By Nim and All: {}", mahasiswa.get());

        /**
         * hasil:
         * 19:11:46.156 [main] INFO com.tutorial.query.hql.SelectStatementHQLTest - Find By Nim: 1511510105
         * 19:11:46.158 [main] INFO com.tutorial.query.hql.SelectStatementHQLTest - Find By Nim and All: Mahasiswa(id=3, nim=1511510105, nama=fahmi, thnMasuk=2015, tglLahir=1997-03-22, create_at=2023-03-24T15:01:04.996546, update_at=2023-03-24T15:01:04.996546, active=false, biodata=null)
         */
    }

    @Test
    void testFindByNameOrTahunMasuk() {

        var nama1 = "agung rianto (Updated)";
        var nama2 = "fahmi";
        var tahunMasuk1 = 2015;
        var tahunMasuk2 = 2015;

        List<Mahasiswa> data = this.statementDao.findByNamaOrTahunMasuk(nama2, tahunMasuk2);

        Assertions.assertEquals(2, data.size());

        log.info("Data Mahasiswa= {}", data);

        /**
         * Result:
         * 23:01:50.204 [main] INFO com.tutorial.query.hql.SelectStatementHQLTest - Data Mahasiswa= [Mahasiswa(id=2, nim=1511510104, nama=agung rianto (Updated), thnMasuk=2015, tglLahir=1996-03-22, create_at=2023-03-24T11:42:00.865907, update_at=2023-03-24T11:42:00.865907, active=false, biodata=null), Mahasiswa(id=3, nim=1511510105, nama=fahmi, thnMasuk=2015, tglLahir=1997-03-22, create_at=2023-03-24T15:01:04.996546, update_at=2023-03-24T15:01:04.996546, active=false, biodata=null)]
         *
         * Query yang dilakukan hibernate:
         * 23:03:40.497 [main] DEBUG org.hibernate.SQL -
         *     select
         *         mahasiswa0_.id as id1_19_,
         *         mahasiswa0_.is_active as is_activ2_19_,
         *         mahasiswa0_.bio as bio3_19_,
         *         mahasiswa0_.create_at as create_a4_19_,
         *         mahasiswa0_.nama_mahasiswa as nama_mah5_19_,
         *         mahasiswa0_.nim_mahasiswa as nim_maha6_19_,
         *         mahasiswa0_.tanggal_lahir as tanggal_7_19_,
         *         mahasiswa0_.tahun_masuk as tahun_ma8_19_,
         *         mahasiswa0_.update_at as update_a9_19_
         *     from
         *         mhs mahasiswa0_
         *     where
         *         mahasiswa0_.nama_mahasiswa=?
         *         or mahasiswa0_.tahun_masuk=?
         * Hibernate:
         *     select
         *         mahasiswa0_.id as id1_19_,
         *         mahasiswa0_.is_active as is_activ2_19_,
         *         mahasiswa0_.bio as bio3_19_,
         *         mahasiswa0_.create_at as create_a4_19_,
         *         mahasiswa0_.nama_mahasiswa as nama_mah5_19_,
         *         mahasiswa0_.nim_mahasiswa as nim_maha6_19_,
         *         mahasiswa0_.tanggal_lahir as tanggal_7_19_,
         *         mahasiswa0_.tahun_masuk as tahun_ma8_19_,
         *         mahasiswa0_.update_at as update_a9_19_
         *     from
         *         mhs mahasiswa0_
         *     where
         *         mahasiswa0_.nama_mahasiswa=?
         *         or mahasiswa0_.tahun_masuk=?
         *
         */

    }


}
