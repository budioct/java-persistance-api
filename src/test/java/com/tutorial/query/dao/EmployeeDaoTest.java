package com.tutorial.query.dao;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.query.mapping.onetomany.entity.Employee;
import com.tutorial.query.mapping.onetomany.dao.EmployeeDao;
import com.tutorial.query.mapping.onetomany.entity.Manager;
import com.tutorial.query.mapping.onetomany.dao.ManagerDao;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class EmployeeDaoTest {

    private final static Logger log = LoggerFactory.getLogger(EmployeeDaoTest.class);

    private Session session;

    private EmployeeDao employeeDao;

    private ManagerDao managerDao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession();
        this.employeeDao = new EmployeeDao(session);
        this.managerDao = new ManagerDao(session);
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    @Disabled
    void testConnectionHibernate(){
        this.session.beginTransaction();
    }

    @Test
    void testSaveEmployeeDao(){

        this.session.beginTransaction();

        Manager sinta = Manager.builder()
                .fullName("sinta")
                .salary(5_000_000L)
                .streetAddress("Jl. printis 3")
                .build();
         sinta = this.managerDao.save(sinta);

        Employee jamal = Employee.builder()
                .manager(sinta)
                .fullName("jamal")
                .salary(5_000_000L)
                .streetAddress("Jl. merdeka 45")
                .build();
         jamal = this.employeeDao.save(jamal);

        Employee mila = Employee.builder()
                .manager(sinta)
                .fullName("mila")
                .salary(5_000_000L)
                .streetAddress("Jl. lato lato 12")
                .build();
        mila = this.employeeDao.save(mila);

        Employee husein = Employee.builder()
                .manager(sinta)
                .fullName("husein")
                .salary(5_500_000L)
                .streetAddress("Jl. hl sabah 07")
                .build();
        husein = this.employeeDao.save(husein);

        Assertions.assertEquals("sinta", sinta.getFullName());
        Assertions.assertEquals("jamal", jamal.getFullName());
        Assertions.assertEquals(5_000_000L, mila.getSalary());
        Assertions.assertEquals("Jl. hl sabah 07", husein.getStreetAddress());

        this.session.getTransaction().commit();

        log.info("Save Employee", jamal);
        log.info("Save Employee", mila);
        log.info("Save Employee", husein);
        log.info("Save Manager", sinta);

    }


}
