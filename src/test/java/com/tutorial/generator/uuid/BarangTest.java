package com.tutorial.generator.uuid;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.generator.dao.BarangDao;
import com.tutorial.generator.entity.Barang;
import com.tutorial.simple.dao.MahasiswaDao;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class BarangTest {

    private final static Logger log = LoggerFactory.getLogger(BarangTest.class);

    private Session session;
    private BarangDao barangDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.barangDao = new BarangDao(session); // masukan Session ke barang DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    @Disabled
    void testConnectionHibernate() {
        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
    }

    @Test
    void testSaveBarang() {
        // init barang // id akan generate otomatis dengan UUID
        Barang barang = Barang.builder()
                .name("LNB SKU")
                .createBy("admin")
                .createdDateTime(LocalDateTime.now())
                .build();

        this.session.beginTransaction(); // Transaction beginTransaction() // memulai session mencakup beberapa transaksis gagasan session, antara entity dan datastore
        barang = this.barangDao.save(barang); // Barang save(Barang value)
        this.session.getTransaction().commit(); // void commit() // eksekusi session
        log.info("Save Barang: {}", barang);

        /**
         * result hibernate dan inject data:
         * 09:13:58.996 [main] INFO com.tutorial.generator.uuid.BarangTest - Save Barang: Barang(id=4fc01fc6-e3a9-4a91-bc6b-dee2a764c5db, name=Decoder, createBy=admin, createdDateTime=2023-03-25T09:13:58.930537500, lastUpdateBy=null, lastUpdatedBy=null)
         * 09:15:34.818 [main] INFO com.tutorial.generator.uuid.BarangTest - Save Barang: Barang(id=52aa7187-ca51-4a1a-bcd6-f750a260e9c9, name=LNB Sigle, createBy=admin, createdDateTime=2023-03-25T09:15:34.738501600, lastUpdateBy=null, lastUpdatedBy=null)
         * 09:16:05.934 [main] INFO com.tutorial.generator.uuid.BarangTest - Save Barang: Barang(id=d4b600de-c259-4f65-a4eb-bc45d6433b8c, name=LNB Dual, createBy=admin, createdDateTime=2023-03-25T09:16:05.837801600, lastUpdateBy=null, lastUpdatedBy=null)
         * 09:16:35.608 [main] INFO com.tutorial.generator.uuid.BarangTest - Save Barang: Barang(id=db9d7b64-5828-4f58-b236-4dded88cf9ea, name=LNB SKU, createBy=admin, createdDateTime=2023-03-25T09:16:35.518407300, lastUpdateBy=null, lastUpdatedBy=null)
         * 09:13:58.782 [main] DEBUG org.hibernate.SQL -
         *
         *     create table kelas_uuid (
         *        id varchar(255) not null,
         *         create_by varchar(100) not null,
         *         created_datetime datetime(6) not null,
         *         last_updated_by varchar(100),
         *         last_updated_datetime datetime(6),
         *         nama varchar(10),
         *         primary key (id)
         *     ) engine=InnoDB
         * Hibernate:
         *
         *     create table kelas_uuid (
         *        id varchar(255) not null,
         *         create_by varchar(100) not null,
         *         created_datetime datetime(6) not null,
         *         last_updated_by varchar(100),
         *         last_updated_datetime datetime(6),
         *         nama varchar(10),
         *         primary key (id)
         *     ) engine=InnoDB
         */

    }


}
