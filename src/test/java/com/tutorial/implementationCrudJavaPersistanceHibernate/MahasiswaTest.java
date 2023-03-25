package com.tutorial.implementationCrudJavaPersistanceHibernate;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.simple.dao.MahasiswaDao;
import com.tutorial.simple.master.Mahasiswa;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class MahasiswaTest {

    private final static Logger log = LoggerFactory.getLogger(MahasiswaTest.class);

    private Session session;
    private MahasiswaDao mahasiswaDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.mahasiswaDao = new MahasiswaDao(session); // masukan Session ke mahasiswa DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    @Disabled
    void testConnectionHibernate(){
        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
    }

    @Test
    void testSaveMahasiswa(){
        // init mahasiswa.. constraint tidak boleh duplicat dalam table
        Mahasiswa mahasiswa = Mahasiswa.builder()
                .id(3L)
                .nim("1511510105")
                .nama("fahmi")
                .active(false)
                .create_at(LocalDateTime.now())
                .update_at(LocalDateTime.now())
                .tglLahir(LocalDate.of(1997, 03, 22))
                .thnMasuk(2015).build();



        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore

        mahasiswa = this.mahasiswaDao.save(mahasiswa); // Mahasiswa save(Mahasiswa value) // save implementasi dari dao

        this.session.getTransaction().commit(); // void commit() // eksekusi session

        log.info("mahasiswa save: {}", mahasiswa);

        /**
         * hasil akan mengeluarkan data dan sql
         * 11:08:20.858 [main] INFO com.tutorial.testconnection.mahasiswa.MahasiswaTest - mahasiswa save: Mahasiswa(id=2, nim=1511510104, nama=agung rianto, thnMasuk=2015, tglLahir=1996-03-22, create_at=2023-03-24T11:08:20.764699600, update_at=2023-03-24T11:08:20.764699600, active=false, biodata=null)
         * 11:41:18.206 [main] DEBUG org.hibernate.SQL -
         *     insert
         *     into
         *         mhs
         *         (is_active, bio, create_at, nama_mahasiswa, nim_mahasiswa, tanggal_lahir, tahun_masuk, update_at, id)
         *     values
         *         (?, ?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         mhs
         *         (is_active, bio, create_at, nama_mahasiswa, nim_mahasiswa, tanggal_lahir, tahun_masuk, update_at, id)
         *     values
         *         (?, ?, ?, ?, ?, ?, ?, ?, ?)
         */

    }

    @Test
    void testUpdateMahasiswa(){

        // init mahasiswa
        Mahasiswa mahasiswa = Mahasiswa.builder()
                .id(2L)
                .nim("1511510104")
                .nama("agung rianto (Updated)")
                .active(false)
                .create_at(LocalDateTime.now())
                .update_at(LocalDateTime.now())
                .tglLahir(LocalDate.of(1996, 03, 22))
                .thnMasuk(2015).build();

        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore

        mahasiswa = this.mahasiswaDao.update(mahasiswa); // Mahasiswa update(Mahasiswa value) // save implementasi dari dao

        this.session.getTransaction().commit(); // void commit() // eksekusi session

        log.info("mahasiswa save: {}", mahasiswa);

        /**
         * hasil akan mengeluarkan data dan sql
         * 11:39:51.258 [main] INFO com.tutorial.testconnection.mahasiswa.MahasiswaTest - mahasiswa save: Mahasiswa(id=2, nim=1511510104, nama=agung rianto (Updated), thnMasuk=2015, tglLahir=1996-03-22, create_at=2023-03-24T11:39:51.160234300, update_at=2023-03-24T11:39:51.160234300, active=false, biodata=null)
         * 11:42:00.911 [main] DEBUG org.hibernate.SQL -
         *     update
         *         mhs
         *     set
         *         is_active=?,
         *         bio=?,
         *         create_at=?,
         *         nama_mahasiswa=?,
         *         nim_mahasiswa=?,
         *         tanggal_lahir=?,
         *         tahun_masuk=?,
         *         update_at=?
         *     where
         *         id=?
         * Hibernate:
         *     update
         *         mhs
         *     set
         *         is_active=?,
         *         bio=?,
         *         create_at=?,
         *         nama_mahasiswa=?,
         *         nim_mahasiswa=?,
         *         tanggal_lahir=?,
         *         tahun_masuk=?,
         *         update_at=?
         *     where
         *         id=?
         */

    }

    @Test
    void testFindByIdMahasiswa(){
        // init value
        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore

        Long mahasiswaId = 3L;
        Optional<Mahasiswa> id = this.mahasiswaDao.findById(mahasiswaId); // Optional<Mahasiswa> findById(Long value) // mendapatkan id dengan return optional<T>

        // cek optional
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id.isPresent(), "object mahasiswa not null");
        log.info("mahasiswa by id {} => {}", mahasiswaId, id.orElse(null));

        /**
         * result findbyid di genereta otomatis
         *
         * 11:53:38.279 [main] DEBUG com.tutorial.testconnection.mahasiswa.MahasiswaTest - mahasiswa by id 1 => Mahasiswa(id=1, nim=1511510099, nama=budhi, thnMasuk=2015, tglLahir=1996-10-22, create_at=2023-03-24T11:04:42.558559, update_at=2023-03-24T11:04:42.558559, active=false, biodata=null)
         *
         * 11:53:38.251 [main] DEBUG org.hibernate.SQL -
         *     select
         *         mahasiswa0_.id as id1_0_0_,
         *         mahasiswa0_.is_active as is_activ2_0_0_,
         *         mahasiswa0_.bio as bio3_0_0_,
         *         mahasiswa0_.create_at as create_a4_0_0_,
         *         mahasiswa0_.nama_mahasiswa as nama_mah5_0_0_,
         *         mahasiswa0_.nim_mahasiswa as nim_maha6_0_0_,
         *         mahasiswa0_.tanggal_lahir as tanggal_7_0_0_,
         *         mahasiswa0_.tahun_masuk as tahun_ma8_0_0_,
         *         mahasiswa0_.update_at as update_a9_0_0_
         *     from
         *         mhs mahasiswa0_
         *     where
         *         mahasiswa0_.id=?
         * Hibernate:
         *     select
         *         mahasiswa0_.id as id1_0_0_,
         *         mahasiswa0_.is_active as is_activ2_0_0_,
         *         mahasiswa0_.bio as bio3_0_0_,
         *         mahasiswa0_.create_at as create_a4_0_0_,
         *         mahasiswa0_.nama_mahasiswa as nama_mah5_0_0_,
         *         mahasiswa0_.nim_mahasiswa as nim_maha6_0_0_,
         *         mahasiswa0_.tanggal_lahir as tanggal_7_0_0_,
         *         mahasiswa0_.tahun_masuk as tahun_ma8_0_0_,
         *         mahasiswa0_.update_at as update_a9_0_0_
         *     from
         *         mhs mahasiswa0_
         *     where
         *         mahasiswa0_.id=?
         */

    }

    @Test
    void testDeleteMahasiswa(){

        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore

        // cara 1
//        Mahasiswa id = this.session.get(Mahasiswa.class, 3L); // <T> T get(Class<T> var1, Serializable var2) // param 1 get detail class dengan java Reflection Class<T>, param 2 get id lewat table db
//        this.mahasiswaDao.removeBy(id); // boolean removeBy(Mahasiswa value) // hapus
//        this.session.getTransaction().commit(); // void commit() // eksekusi session

        // cara 2
        Optional<Mahasiswa> idOptional = this.mahasiswaDao.findById(1L); // Optional<Mahasiswa> findById(Long value) // mencari id dengan return Optional<T>
        Assertions.assertTrue(idOptional.isPresent(), "mahasiswa is present"); // void assertTrue(boolean condition, String message) // jika tidak ada akan false
        if (idOptional.isPresent()){
            this.mahasiswaDao.removeBy(idOptional.get()); // removeBy(Mahasiswa value)// hapus data berdasarkan Id // T get()  // return object Type
            this.session.getTransaction().commit(); // void commit() // eksekusi session
        }

        /**
         * Result hasil hapus data berdasarkan Id
         * 12:54:10.549 [main] DEBUG org.hibernate.SQL -
         *     delete
         *     from
         *         mhs
         *     where
         *         id=?
         * Hibernate:
         *     delete
         *     from
         *         mhs
         *     where
         *         id=?
         */


    }




}
