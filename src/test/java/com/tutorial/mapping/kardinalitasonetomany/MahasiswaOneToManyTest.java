package com.tutorial.mapping.kardinalitasonetomany;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.mapping.onetomany.dao.AlamatOneToManyDao;
import com.tutorial.mapping.onetomany.dao.MahasiswaOneToManyDao;
import com.tutorial.mapping.onetomany.entity.AlamatOneToMany;
import com.tutorial.mapping.onetomany.entity.MahasiswaOneToMany;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MahasiswaOneToManyTest {

    private static final Logger log = LoggerFactory.getLogger(MahasiswaOneToManyTest.class);

    private Session session;

    private MahasiswaOneToManyDao mahasiswaDao;

    private AlamatOneToManyDao alamatDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.mahasiswaDao = new MahasiswaOneToManyDao(session); // masukan Session ke mahasiswa DAO
        this.alamatDao = new AlamatOneToManyDao(session); // masukan Session ke mahasiswa DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    void testConnection(){
       this.session.beginTransaction();

        /**
         * result dari orm yang di buat table baru untuk relasi antar 2 table,
         * mahasiswa dan alamat melalui table mahasiswa_onetomany_alamat_list
         *
         * 08:34:06.774 [main] DEBUG org.hibernate.SQL -
         *     alter table mahasiswa_onetomany_alamat_list
         *        add constraint fk_alamat_id
         *        foreign key (alamat_id)
         *        references alamat_onetomany (id)
         *
         * 08:34:06.839 [main] DEBUG org.hibernate.SQL -
         *     alter table mahasiswa_onetomany_alamat_list
         *        add constraint fk_mahasiswa_id
         *        foreign key (mahasiswa_id)
         *        references mahasiswa_onetomany (id)
         */

    }

    @Test
    void testSaveOnetoManyValid(){
        // init save
        this.session.beginTransaction();
        AlamatOneToMany alamat1 = AlamatOneToMany.builder()
                .provinsi("jawa barat")
                .kota("bandung")
                .kelurahan("ale ale cisoka")
                .kecamatan("cisoka selatan")
                .rw("86")
                .rt("97")
                .kodePos(151516)
                .namaJalan("jl. merdeka 17")
                .build();
        alamat1 = this.alamatDao.save(alamat1);

        AlamatOneToMany alamat2 = AlamatOneToMany.builder()
                .provinsi("jawa barat")
                .kota("bandung")
                .kelurahan("ale ale batu")
                .kecamatan("batu utara")
                .rw("86")
                .rt("97")
                .kodePos(151516)
                .namaJalan("jl. bandeng 17")
                .build();
        alamat2 = this.alamatDao.save(alamat2);

        AlamatOneToMany alamat3 = AlamatOneToMany.builder()
                .provinsi("jawa tengah")
                .kota("semarang")
                .kelurahan("ale ale kerapyak")
                .kecamatan("kerapyak utara")
                .rw("86")
                .rt("97")
                .kodePos(151516)
                .namaJalan("jl. mandala 14")
                .build();
        alamat3 = this.alamatDao.save(alamat3);

        MahasiswaOneToMany kamal = MahasiswaOneToMany.builder()
                .nim(1111111111L)
                .nama("kamal")
                .tanggalLahir(LocalDate.of(1988, 2, 22))
                .tahunMasuk(2012)
                .listAlamat(List.of(alamat1, alamat2, alamat3))
                .build();
        kamal = this.mahasiswaDao.save(kamal);

        Optional<MahasiswaOneToMany> optional = this.mahasiswaDao.findById(kamal.getId()); // Optional<MahasiswaOneToMany> findById(String value)
        Assertions.assertTrue(optional.isPresent(), "mahasiswa tidak ada");

        MahasiswaOneToMany simpanMahasiswa = optional.get();
        Assertions.assertEquals(kamal.getNama(), simpanMahasiswa.getNama()); // void assertEquals(Object expected, Object actual)

        int size = simpanMahasiswa.getListAlamat().size(); // int size() // implementasi dari collection List<T> mendapatkan ukuran data
        Assertions.assertEquals(3, size); // void assertEquals(int expected, int actual)

        this.session.getTransaction().commit();

        log.info("Alamat 1 = {}", alamat1);
        log.info("Alamat 2 = {}", alamat2);
        log.info("Alamat 3 = {}", alamat3);
        log.info("Save Mahasiswa = {}", kamal);

        /**
         * result:
         * 08:52:35.086 [main] INFO com.tutorial.mapping.kardinalitasonetomany.MahasiswaOneToManyTest - Alamat 1 = AlamatOneToMany(id=9159659b-644f-45e2-98d1-fa5002c07185, provinsi=jawa barat, kota=bandung, kelurahan=ale ale cisoka, kecamatan=cisoka selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. merdeka 17)
         * 08:52:35.101 [main] INFO com.tutorial.mapping.kardinalitasonetomany.MahasiswaOneToManyTest - Alamat 2 = AlamatOneToMany(id=53bb60c5-8275-44bc-8aa2-fe7ad823eff6, provinsi=jawa barat, kota=bandung, kelurahan=ale ale batu, kecamatan=batu utara, rw=86, rt=97, kodePos=151516, namaJalan=jl. bandeng 17)
         * 08:52:35.101 [main] INFO com.tutorial.mapping.kardinalitasonetomany.MahasiswaOneToManyTest - Alamat 3 = AlamatOneToMany(id=c83630d6-87b3-480d-8201-6b8117c506b8, provinsi=jawa tengah, kota=semarang, kelurahan=ale ale kerapyak, kecamatan=kerapyak utara, rw=86, rt=97, kodePos=151516, namaJalan=jl. mandala 14)
         * 08:52:35.101 [main] INFO com.tutorial.mapping.kardinalitasonetomany.MahasiswaOneToManyTest - Save Mahasiswa = MahasiswaOneToMany(id=1cd3ea90-b751-49c8-a457-540af122c8d0, nim=1111111111, nama=kamal, tanggalLahir=1988-02-22, tahunMasuk=2012, listAlamat=[AlamatOneToMany(id=9159659b-644f-45e2-98d1-fa5002c07185, provinsi=jawa barat, kota=bandung, kelurahan=ale ale cisoka, kecamatan=cisoka selatan, rw=86, rt=97, kodePos=151516, namaJalan=jl. merdeka 17), AlamatOneToMany(id=53bb60c5-8275-44bc-8aa2-fe7ad823eff6, provinsi=jawa barat, kota=bandung, kelurahan=ale ale batu, kecamatan=batu utara, rw=86, rt=97, kodePos=151516, namaJalan=jl. bandeng 17), AlamatOneToMany(id=c83630d6-87b3-480d-8201-6b8117c506b8, provinsi=jawa tengah, kota=semarang, kelurahan=ale ale kerapyak, kecamatan=kerapyak utara, rw=86, rt=97, kodePos=151516, namaJalan=jl. mandala 14)])
         */

    }



}
