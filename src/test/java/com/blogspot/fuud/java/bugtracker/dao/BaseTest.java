package com.blogspot.fuud.java.bugtracker.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    private SessionFactory sessionFactory;
    private Session session;

    protected Session getSession() {
        return session;
    }

    @Before
	public void setUp() throws Exception {
		// A SessionFactory is set up once for an application
        final Configuration configuration = new Configuration();
        sessionFactory = configuration
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }

	@After
	public  void tearDown() throws Exception {
        if (session!=null){
            session.close();
        }
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
}
