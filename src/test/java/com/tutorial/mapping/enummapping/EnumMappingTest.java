package com.tutorial.mapping.enummapping;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.mapping.enummapping.dao.EmployeeEnumOrdinalDao;
import com.tutorial.mapping.enummapping.entity.EmployeeEnumOrdinal;
import com.tutorial.mapping.enummapping.entity.EmployeeStatus;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class EnumMappingTest {

    private static final Logger log = LoggerFactory.getLogger(EnumMappingTest.class);

    private Session session;

    private EmployeeEnumOrdinalDao dao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession(); // koneksi ke DBMS lewat ORM
        this.dao = new EmployeeEnumOrdinalDao(session); // masukan Session ke mahasiswa DAO
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    void testSaveEnumMapping(){
        // init object
        EmployeeEnumOrdinal ordinal = EmployeeEnumOrdinal.builder()
                .name("marwan")
                .status(EmployeeStatus.OUT_OF_TOWN_DUTY)
                .tglLahir(LocalDate.of(1994, 10, 22))
                .build();

        this.session.beginTransaction();
        ordinal = this.dao.save(ordinal);
        this.session.getTransaction().commit();

        log.info("Save Enum Mapping Ordinal {}", ordinal);

        /**
         * result save:
         * 15:44:35.673 [main] INFO com.tutorial.mapping.enummapping.EnumMappingTest - Save Enum Mapping Ordinal EmployeeEnumOrdinal(id=1, name=budhi, tglLahir=1996-10-22, status=RESIGN)
         */
    }

}
