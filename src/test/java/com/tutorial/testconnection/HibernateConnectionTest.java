package com.tutorial.testconnection;

import com.tutorial.config.HibernateConfiguration;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateConnectionTest {

    private final static Logger log = LoggerFactory.getLogger(HibernateConnectionTest.class);

    private Session session;

    @BeforeEach
    void setUp() {
        log.info("ini hibernate session");
        this.session = HibernateConfiguration.getSession();
    }

    @AfterEach
    void tearDown() {
        log.info("destroy hibernate session!");
        this.session.close();
    }

    @Test
    void testConnectionHibernate(){
        this.session.beginTransaction();
    }


}
