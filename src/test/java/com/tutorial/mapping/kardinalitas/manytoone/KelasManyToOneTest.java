package com.tutorial.mapping.kardinalitas.manytoone;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.mapping.manytoone.dao.KelasManyToOneDao;
import com.tutorial.mapping.manytoone.dao.MahasiswaManyToOneDao;
import com.tutorial.mapping.manytoone.entity.KelasManyToOne;
import com.tutorial.mapping.manytoone.entity.MahasiswaManyToOne;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Optional;

public class KelasManyToOneTest {

    private static final Logger log = LoggerFactory.getLogger(KelasManyToOneTest.class);

    private Session session;
    private KelasManyToOneDao kelasDao;
    private MahasiswaManyToOneDao mahasiswaDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.kelasDao = new KelasManyToOneDao(session); // masukan Session ke mahasiswa DAO
        this.mahasiswaDao = new MahasiswaManyToOneDao(session); // masukan Session ke mahasiswa DAO
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

        /**
         * // satu kelas banyak mahasiswa // setiap mahasiswa hanya punya satu kelas
         * hasil contraint foreign key yang dari table kelas di table mahasiswa
         * 09:46:47.702 [main] DEBUG org.hibernate.SQL -
         * <p>
         * alter table mahasiswa_manytoone
         * add constraint FKh6iknygxm9q16tarrcnor18fs
         * foreign key (kelas_id)
         * references kelas_manytoone (id)
         */
    }

    @Test
    void testSaveOneKelasManyMahasiswa() {
        // ini data
        this.session.beginTransaction();

        KelasManyToOne pbol = KelasManyToOne.builder()
                .nama("Pemograman Berbasis Object Lanjutan")
                .angkata(2016)
                .programStudi("Teknik Informatika")
                .build();
        pbol = this.kelasDao.save(pbol);

        MahasiswaManyToOne akmal = MahasiswaManyToOne.builder()
                .kelas(pbol) // kita perlu menyimpan untuk foreign key di table mahasiswa
                .nim(1611518767L)
                .nama("akmal")
                .tanggalLahir(LocalDate.of(1996, 10, 13))
                .tahunMasuk(2016)
                .build();
        akmal = this.mahasiswaDao.save(akmal);

        MahasiswaManyToOne putri = MahasiswaManyToOne.builder()
                .kelas(pbol) // kita perlu menyimpan untuk foreign key di table mahasiswa
                .nim(1611518567L)
                .nama("putri")
                .tanggalLahir(LocalDate.of(1999, 10, 6))
                .tahunMasuk(2016)
                .build();
        putri = this.mahasiswaDao.save(putri);

//        pbol.setListMahasiswa(List.of(akmal, putri)); // void setListMahasiswa(final List<MahasiswaManyToOne> listMahasiswa) // kita perlu menyimpan untuk foreign key di table kelas

        Optional<KelasManyToOne> findId = this.kelasDao.findById(pbol.getId()); // Optional<KelasManyToOne> findById(String value)
        Assertions.assertTrue(findId.isPresent(), "Id Kelas tidak ada"); // void assertTrue(boolean condition, String message)

        KelasManyToOne data = findId.get(); // T get() // return Type untuk reusable
        Assertions.assertEquals(data.getNama(), pbol.getNama()); // void assertEquals(Object expected, Object actual) // cek apakah ekpetasi kita sama dengan data sebenernaya

        this.session.getTransaction().commit(); // void commit() // eksekusi

        log.info("Mahasiswa Satu= {}", akmal);
        log.info("Mahasiswa Satu= {}", putri);
        log.info("Save Kelas= {}", pbol);

        /**
         * jika Error: Caused by: java.sql.SQLIntegrityConstraintViolationException: Column 'kelas_id' cannot be null -> foreign yang ada di mahasiswa belum di inject
         *
         * result Save:
         * 10:38:11.701 [main] INFO com.tutorial.mapping.kardinalitasmanytoone.KelasManyToOneTest - Mahasiswa Satu= MahasiswaManyToOne(id=79c79a7f-1e6e-40de-8f9d-c10ce7042c31, nim=1611518767, nama=akmal, tanggalLahir=1996-10-13, tahunMasuk=2016, kelas=KelasManyToOne(id=3135e7d2-d74a-47cf-a2b9-704a696bd856, nama=Pemograman Berbasis Object Lanjutan, angkata=2016, programStudi=Teknik Informatika, listMahasiswa=null))
         * 10:38:11.716 [main] INFO com.tutorial.mapping.kardinalitasmanytoone.KelasManyToOneTest - Mahasiswa Satu= MahasiswaManyToOne(id=36cf9f1f-793d-4f3f-a932-fd67da0a7c6e, nim=1611518567, nama=putri, tanggalLahir=1999-10-06, tahunMasuk=2016, kelas=KelasManyToOne(id=3135e7d2-d74a-47cf-a2b9-704a696bd856, nama=Pemograman Berbasis Object Lanjutan, angkata=2016, programStudi=Teknik Informatika, listMahasiswa=null))
         * 10:38:11.717 [main] INFO com.tutorial.mapping.kardinalitasmanytoone.KelasManyToOneTest - Save Kelas= KelasManyToOne(id=3135e7d2-d74a-47cf-a2b9-704a696bd856, nama=Pemograman Berbasis Object Lanjutan, angkata=2016, programStudi=Teknik Informatika, listMahasiswa=null)
         *
         */

    }

    @Test
    void findByIdKelas(){

        this.session.beginTransaction();

        var data = "3135e7d2-d74a-47cf-a2b9-704a696bd856"; // data valid
        var data2 = "3135e7d2-d74a-47cf-a2b9-704a696bd8563dfvdf"; // data invalid

        Optional<KelasManyToOne> byId = this.kelasDao.findById(data); // Optional<KelasManyToOne> findById(String value)
        KelasManyToOne kelasManyToOne = byId.get(); // T get()

        // cek kebenaran
        Assertions.assertTrue(byId.isPresent(), "Id tidak ada"); // void assertTrue(boolean condition, String message)
        Assertions.assertSame(data, kelasManyToOne.getId()); // void assertSame(Object expected, Object actual) //
        Assertions.assertNotSame(data2, kelasManyToOne.getId()); // void assertSame(Object expected, Object actual)

        this.session.getTransaction().commit(); // void commit()


    }



}
