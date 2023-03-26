package com.tutorial.mapping.kardinalitas.manytomany;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.mapping.manytomany.dao.MahasiswaManyToManyDao;
import com.tutorial.mapping.manytomany.dao.MataKuliahManyToManyDao;
import com.tutorial.mapping.manytomany.entity.MahasiswaManyToMany;
import com.tutorial.mapping.manytomany.entity.MataKuliahManyToMany;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MahasiswaManyToManyTest {

    private static final Logger log = LoggerFactory.getLogger(MahasiswaManyToManyTest.class);

    private Session session;
    private MahasiswaManyToManyDao mahasiswaDao;
    private MataKuliahManyToManyDao matakuliahDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.mahasiswaDao = new MahasiswaManyToManyDao(session); // masukan Session ke mahasiswa DAO
        this.matakuliahDao = new MataKuliahManyToManyDao(session); // masukan Session ke mahasiswa DAO
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
         * // banyak mahasiswa mengabil banyak mata kuliah // banyaknya mata kuliah bisa di ambil beberapa mahasiswa yang belum ambil kelas
         * hasil generate table
         *
         * 11:38:56.236 [main] DEBUG org.hibernate.SQL -
         *     create table mahasiswa_manytomamy (
         *        id varchar(255) not null,
         *         nama varchar(40),
         *         nim bigint,
         *         tahun_masuk integer,
         *         tangga_lahir date,
         *         primary key (id)
         *     ) engine=InnoDB
         *
         * 11:38:56.268 [main] DEBUG org.hibernate.SQL -
         * create table matakuliah_manytomamy (
         *        id varchar(255) not null,
         *         nama varchar(40),
         *         sks integer,
         *         primary key (id)
         *     ) engine=InnoDB
         *
         * hasil contraint foreign key yang dari table kelas di table mahasiswa
         *
         * 11:38:56.382 [main] DEBUG org.hibernate.SQL -
         *     alter table mahasiswa_ambil_matakuliah
         *        add constraint fk_mahasiswa_kuliah_matakuliah_id
         *        foreign key (matakuliah_id)
         *        references matakuliah_manytomamy (id)
         *
         * 11:38:56.508 [main] DEBUG org.hibernate.SQL -
         *     alter table mahasiswa_ambil_matakuliah
         *        add constraint fk_mahasiswa_kuliah_mahasiswa_id
         *        foreign key (mahasiswa_id)
         *        references mahasiswa_manytomamy (id)
         *
         */
    }

    @Test
    void testSaveMahasiswaValid() {

        this.session.beginTransaction();

        // init data matkul
        MataKuliahManyToMany pemogramanJava = MataKuliahManyToMany.builder()
                .sks(3)
                .nama("Pemograman Java 1")
                .build();
        pemogramanJava = this.matakuliahDao.save(pemogramanJava);

        MataKuliahManyToMany perancanganBasisData = MataKuliahManyToMany.builder()
                .sks(3)
                .nama("Perancangan Basis Data 1")
                .build();
        perancanganBasisData = this.matakuliahDao.save(perancanganBasisData);

        // init kita masukan keldalam Set<T>
        Set<MataKuliahManyToMany> ambilmatakuliah = new HashSet<>();
        ambilmatakuliah.add(pemogramanJava);
        ambilmatakuliah.add(perancanganBasisData);

        MahasiswaManyToMany yanto = MahasiswaManyToMany.builder()
                .nama("yanto")
                .tahunMasuk(2014)
                .nim(1411410086L)
                .tanggalLahir(LocalDate.of(1995, 2, 14))
                .listMataKuliah(ambilmatakuliah) // setListMahasiswa(final Set<MahasiswaManyToMany> listMahasiswa) // MataKuliahManyToManyBuilder listMahasiswa(final Set<MahasiswaManyToMany> listMahasiswa)
                .build();
        yanto = this.mahasiswaDao.save(yanto);

        MahasiswaManyToMany yanti = MahasiswaManyToMany.builder()
                .nama("yanti")
                .tahunMasuk(2015)
                .nim(1511510024L)
                .tanggalLahir(LocalDate.of(1996, 2, 17))
                .listMataKuliah(ambilmatakuliah) // setListMahasiswa(final Set<MahasiswaManyToMany> listMahasiswa) // MataKuliahManyToManyBuilder listMahasiswa(final Set<MahasiswaManyToMany> listMahasiswa)
                .build();
        yanti = this.mahasiswaDao.save(yanti);

        this.session.getTransaction().commit();

        log.info("Mata Kuliah 1 = {}", pemogramanJava);
        log.info("Mata Kuliah 2 = {}", perancanganBasisData);
        log.info("Mahasiswa ambil Mata Kuliah 1 = {}", yanto);
        log.info("Mahasiswa ambil Mata Kuliah 1 = {}", yanti);

        /**
         * return hasil save
         * 12:05:20.419 [main] INFO com.tutorial.mapping.kardinalitas.manytomany.MahasiswaManyToManyTest - Mata Kuliah 1 = MataKuliahManyToMany(id=6e58edb4-2396-4422-86f1-02e737b932eb, nama=Pemograman Java 1, sks=3)
         * 12:05:20.421 [main] INFO com.tutorial.mapping.kardinalitas.manytomany.MahasiswaManyToManyTest - Mata Kuliah 2 = MataKuliahManyToMany(id=1731e1f2-b47f-48eb-a6fd-92ce58952f4e, nama=Perancangan Basis Data 1, sks=3)
         * 12:05:20.421 [main] INFO com.tutorial.mapping.kardinalitas.manytomany.MahasiswaManyToManyTest - Mahasiswa ambil Mata Kuliah 1 = MahasiswaManyToMany(id=d4760e0b-99d4-49e2-8a49-1ce00b7b0030, nim=1411410086, nama=yanto, tanggalLahir=1995-02-14, tahunMasuk=2014)
         * 12:05:20.431 [main] INFO com.tutorial.mapping.kardinalitas.manytomany.MahasiswaManyToManyTest - Mahasiswa ambil Mata Kuliah 1 = MahasiswaManyToMany(id=5c63e321-bc21-447b-bbb4-54f238737482, nim=1511510024, nama=yanti, tanggalLahir=1996-02-17, tahunMasuk=2015)
         */

    }

    @Test
    void testFindByIdMahasiswa(){

        this.session.beginTransaction();

        var data = "5c63e321-bc21-447b-bbb4-54f238737482"; // valid
        //var data = "d4760e0b-99d4-49e2-8a49-1ce00b7b0030"; // valid

        Optional<MahasiswaManyToMany> findbyid = this.mahasiswaDao.findById(data); // Optional<MahasiswaManyToMany> findById(String value)
        MahasiswaManyToMany mahasiswaManyToMany = findbyid.get(); // T get() // return type (reusable)

        Assertions.assertTrue(findbyid.isPresent(), "Id tidak ada broo!!"); // assertTrue(boolean condition, String message)
        Assertions.assertEquals(data, mahasiswaManyToMany.getId()); // void assertEquals(Object expected, Object actual)
        Assertions.assertSame(data, mahasiswaManyToMany.getId()); // void assertSame(Object expected, Object actual)

        log.info("mahasiswa= " + mahasiswaManyToMany);
        log.info("list mahasiswa {\nsize: {}, \ndata: {}, \n}", mahasiswaManyToMany.getListMataKuliah().size(), mahasiswaManyToMany.getListMataKuliah());

        /**
         * hasil query yang dilakukan ketika idnya ada:
         * 12:35:44.108 [main] DEBUG org.hibernate.SQL -
         *     select
         *         mahasiswam0_.id as id1_12_0_,
         *         mahasiswam0_.nama as nama2_12_0_,
         *         mahasiswam0_.nim as nim3_12_0_,
         *         mahasiswam0_.tahun_masuk as tahun_ma4_12_0_,
         *         mahasiswam0_.tangga_lahir as tangga_l5_12_0_
         *     from
         *         mahasiswa_manytomamy mahasiswam0_
         *     where
         *         mahasiswam0_.id=?
         * Hibernate:
         *     select
         *         mahasiswam0_.id as id1_12_0_,
         *         mahasiswam0_.nama as nama2_12_0_,
         *         mahasiswam0_.nim as nim3_12_0_,
         *         mahasiswam0_.tahun_masuk as tahun_ma4_12_0_,
         *         mahasiswam0_.tangga_lahir as tangga_l5_12_0_
         *     from
         *         mahasiswa_manytomamy mahasiswam0_
         *     where
         *         mahasiswam0_.id=?
         *
         * 12:35:44.182 [main] DEBUG org.hibernate.SQL -
         *     select
         *         listmataku0_.mahasiswa_id as mahasisw1_11_0_,
         *         listmataku0_.matakuliah_id as matakuli2_11_0_,
         *         matakuliah1_.id as id1_17_1_,
         *         matakuliah1_.nama as nama2_17_1_,
         *         matakuliah1_.sks as sks3_17_1_
         *     from
         *         mahasiswa_ambil_matakuliah listmataku0_
         *     inner join
         *         matakuliah_manytomamy matakuliah1_
         *             on listmataku0_.matakuliah_id=matakuliah1_.id
         *     where
         *         listmataku0_.mahasiswa_id=?
         *
         * hasil record yang di tangkap:
         * 12:30:43.013 [main] INFO com.tutorial.mapping.kardinalitas.manytomany.MahasiswaManyToManyTest - mahasiswa= MahasiswaManyToMany(id=5c63e321-bc21-447b-bbb4-54f238737482, nim=1511510024, nama=yanti, tanggalLahir=1996-02-17, tahunMasuk=2015)
         * 12:30:43.025 [main] INFO com.tutorial.mapping.kardinalitas.manytomany.MahasiswaManyToManyTest - list mahasiswa {
         * size: 2,
         * data: [MataKuliahManyToMany(id=1731e1f2-b47f-48eb-a6fd-92ce58952f4e, nama=Perancangan Basis Data 1, sks=3), MataKuliahManyToMany(id=6e58edb4-2396-4422-86f1-02e737b932eb, nama=Pemograman Java 1, sks=3)],
         * }
         *
         */

    }

    @Test
    void testFindByIdMataKuliah(){

        this.session.beginTransaction();

        var data = "1731e1f2-b47f-48eb-a6fd-92ce58952f4e"; // valid
        //var data = "6e58edb4-2396-4422-86f1-02e737b932eb"; // valid

        Optional<MataKuliahManyToMany> findbyid = this.matakuliahDao.findById(data); // Optional<MataKuliahManyToMany> findById(String value)
        MataKuliahManyToMany mataKuliahManyToMany = findbyid.get();


        Assertions.assertTrue(findbyid.isPresent(), "Id tidak ada broo!!"); // assertTrue(boolean condition, String message)
        Assertions.assertEquals(data, mataKuliahManyToMany.getId()); // void assertEquals(Object expected, Object actual)
        Assertions.assertSame(data, mataKuliahManyToMany.getId()); // void assertSame(Object expected, Object actual)

        log.info("matakuliah= " + mataKuliahManyToMany);
        log.info("list mahasiswa {\nsize: {}, \ndata: {}, \n}", mataKuliahManyToMany.getListMahasiswa().size(), mataKuliahManyToMany.getListMahasiswa());

        /**
         * hasil query yang dilakukan ketika idnya ada:
         * 12:34:02.829 [main] DEBUG org.hibernate.SQL -
         *     select
         *         matakuliah0_.id as id1_17_0_,
         *         matakuliah0_.nama as nama2_17_0_,
         *         matakuliah0_.sks as sks3_17_0_
         *     from
         *         matakuliah_manytomamy matakuliah0_
         *     where
         *         matakuliah0_.id=?
         * Hibernate:
         *     select
         *         matakuliah0_.id as id1_17_0_,
         *         matakuliah0_.nama as nama2_17_0_,
         *         matakuliah0_.sks as sks3_17_0_
         *     from
         *         matakuliah_manytomamy matakuliah0_
         *     where
         *         matakuliah0_.id=?
         *
         * 12:34:02.877 [main] DEBUG org.hibernate.SQL -
         *     select
         *         listmahasi0_.matakuliah_id as matakuli2_11_0_,
         *         listmahasi0_.mahasiswa_id as mahasisw1_11_0_,
         *         mahasiswam1_.id as id1_12_1_,
         *         mahasiswam1_.nama as nama2_12_1_,
         *         mahasiswam1_.nim as nim3_12_1_,
         *         mahasiswam1_.tahun_masuk as tahun_ma4_12_1_,
         *         mahasiswam1_.tangga_lahir as tangga_l5_12_1_
         *     from
         *         mahasiswa_ambil_matakuliah listmahasi0_
         *     inner join
         *         mahasiswa_manytomamy mahasiswam1_
         *             on listmahasi0_.mahasiswa_id=mahasiswam1_.id
         *     where
         *         listmahasi0_.matakuliah_id=?
         *
         * hasil record yang di tangkap:
         * 12:28:58.999 [main] INFO com.tutorial.mapping.kardinalitas.manytomany.MahasiswaManyToManyTest - matakuliah= MataKuliahManyToMany(id=1731e1f2-b47f-48eb-a6fd-92ce58952f4e, nama=Perancangan Basis Data 1, sks=3)
         * 12:28:59.011 [main] INFO com.tutorial.mapping.kardinalitas.manytomany.MahasiswaManyToManyTest - list mahasiswa {
         * size: 2,
         * data: [MahasiswaManyToMany(id=d4760e0b-99d4-49e2-8a49-1ce00b7b0030, nim=1411410086, nama=yanto, tanggalLahir=1995-02-14, tahunMasuk=2014), MahasiswaManyToMany(id=5c63e321-bc21-447b-bbb4-54f238737482, nim=1511510024, nama=yanti, tanggalLahir=1996-02-17, tahunMasuk=2015)],
         * }
         *
         */

    }


}
