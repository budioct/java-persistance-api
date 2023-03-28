package com.tutorial.query.hql;

import com.tutorial.config.HibernateConfiguration;
import com.tutorial.query.dto.ArithmeticModelDTO;
import com.tutorial.query.hql.dao.ArithmeticHQLDao;
import com.tutorial.query.hql.dao.CreateUpdateDeleteStatementDao;
import com.tutorial.query.mapping.onetomany.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.List;

@Slf4j
public class ArithmeticDaoHQLTest {

    private Session session;

    private ArithmeticHQLDao dao;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession();
        this.dao = new ArithmeticHQLDao(session);
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
    void testAritmaticDao() {

        this.session.beginTransaction();

        List<ArithmeticModelDTO> data = this.dao.selectWithArithmeticModel();

        log.info("Data: {}", data);

        /**
         * Result:
         * 11:58:57.397 [main] DEBUG org.hibernate.hql.internal.ast.QueryTranslatorImpl - HQL: select new com.tutorial.query.dto.ArithmeticModelDTO(id, fullName, salary * 12L, salary + 10000L) from com.tutorial.query.mapping.onetomany.entity.Employee
         * 11:58:57.423 [main] INFO com.tutorial.query.hql.ArithmeticDaoHQLTest - Data: [ArithmeticModelDTO(id=10, fullName=jamal, salarySetahun=60000000, salaryPlusBonus=5010000), ArithmeticModelDTO(id=11, fullName=mila, salarySetahun=60000000, salaryPlusBonus=5010000), ArithmeticModelDTO(id=12, fullName=husein, salarySetahun=66000000, salaryPlusBonus=5510000)]
         */
    }

    @Test
    void testAritmaticDaoParameter() {

        this.session.beginTransaction();

        var id = 10L;
        List<ArithmeticModelDTO> data = this.dao.selectWithArithmeticModelParameter(id); // List<ArithmeticModelDTO> selectWithArithmeticModelParameter(Long idEmployee)

        Assertions.assertEquals(1, data.size());

        this.session.getTransaction().commit();

        log.info("Data: {}", data);

        /**
         * Result:
         * 12:06:52.354 [main] DEBUG org.hibernate.hql.internal.ast.QueryTranslatorImpl - HQL: select new com.tutorial.query.dto.ArithmeticModelDTO(id, fullName, salary * 12L, salary + 10000L) from com.tutorial.query.mapping.onetomany.entity.Employee where id = : idEmployee
         * 12:06:52.377 [main] INFO com.tutorial.query.hql.ArithmeticDaoHQLTest - Data: [ArithmeticModelDTO(id=10, fullName=jamal, salarySetahun=60000000, salaryPlusBonus=5010000)]
         *
         * SQL
         * 12:06:52.367 [main] DEBUG org.hibernate.SQL -
         *     select
         *         employee0_.id as col_0_0_,
         *         employee0_.full_name as col_1_0_,
         *         employee0_.salary*12 as col_2_0_,
         *         employee0_.salary+10000 as col_3_0_
         *     from
         *         employees employee0_
         *     where
         *         employee0_.id=?
         * Hibernate:
         *     select
         *         employee0_.id as col_0_0_,
         *         employee0_.full_name as col_1_0_,
         *         employee0_.salary*12 as col_2_0_,
         *         employee0_.salary+10000 as col_3_0_
         *     from
         *         employees employee0_
         *     where
         *         employee0_.id=?
         *
         *
         * */
    }

    @Test
    void testHitungSalaryEmployee() {

        this.session.beginTransaction();

        List<Long> salaryEmployee = this.dao.hitungTotalSalarySemuaEmployeeDalamSetahun();

        this.session.getTransaction().commit();

        log.info("Total Gaji Karyawan Dalam Satu Tahun: {}", salaryEmployee);

        /**
         * result:
         * 12:15:25.720 [main] DEBUG org.hibernate.hql.internal.ast.QueryTranslatorImpl - HQL: select sum(salary * 12) from com.tutorial.query.mapping.onetomany.entity.Employee
         * 12:15:25.720 [main] DEBUG org.hibernate.hql.internal.ast.QueryTranslatorImpl - SQL: select sum(employee0_.salary*12) as col_0_0_ from employees employee0_
         * 12:15:25.755 [main] INFO com.tutorial.query.hql.ArithmeticDaoHQLTest - Total Gaji Karyawan Dalam Satu Tahun: [36000000]
         */

    }

    @Test
    void testCariSalaryDiatasKaryawan() {

        this.session.beginTransaction();

        var salary = 12_000_000L;
        List<Employee> salaryDiAtas = this.dao.cariKaryawanYangSalaryDalamSetahunDiatas(salary);

        Assertions.assertEquals(3, salaryDiAtas.size(), "jumlah data salah broo!");
        this.session.getTransaction().commit();
        log.info("CariSalaryDiatasKaryawan: {}", salaryDiAtas);

        /**
         * result:
         * 12:26:51.031 [main] DEBUG org.hibernate.internal.util.EntityPrinter - com.tutorial.query.mapping.onetomany.entity.Manager{streetAddress=Jl. printis 3, fullName=sinta, id=4, employees=<uninitialized>, salary=5000000}
         * 12:26:50.995 [main] INFO com.tutorial.query.hql.ArithmeticDaoHQLTest - CariSalaryDiatasKaryawan: [Employee(id=10, fullName=jamal, salary=1000000, streetAddress=Jl. merdeka 45, manager=Manager(id=4, fullName=sinta, salary=5000000, streetAddress=Jl. printis 3)), Employee(id=11, fullName=mila, salary=1000000, streetAddress=Jl. lato lato 12, manager=Manager(id=4, fullName=sinta, salary=5000000, streetAddress=Jl. printis 3)), Employee(id=12, fullName=husein, salary=1000000, streetAddress=Jl. hl sabah 07, manager=Manager(id=4, fullName=sinta, salary=5000000, streetAddress=Jl. printis 3))]
         *
         * SQL:
         * 12:26:50.978 [main] DEBUG org.hibernate.SQL -
         *     select
         *         manager0_.id as id1_21_0_,
         *         manager0_.full_name as full_nam2_21_0_,
         *         manager0_.salary as salary3_21_0_,
         *         manager0_.street_address as street_a4_21_0_
         *     from
         *         manager manager0_
         *     where
         *         manager0_.id=?
         * Hibernate:
         *     select
         *         manager0_.id as id1_21_0_,
         *         manager0_.full_name as full_nam2_21_0_,
         *         manager0_.salary as salary3_21_0_,
         *         manager0_.street_address as street_a4_21_0_
         *     from
         *         manager manager0_
         *     where
         *         manager0_.id=?
         */

    }


}
