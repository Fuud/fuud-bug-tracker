package com.blogspot.fuud.java.bugtracker.dao;

import org.hibernate.Session;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import java.io.Serializable;

import static junit.framework.Assert.assertEquals;

public class BaseGenericDaoTest {
    private final Mockery mockery = new Mockery();

    @Test
    public void testSave() throws Exception {
        final Session session = mockery.mock(Session.class);
        final Object objectToSave = new Object();
        final Serializable id = 123;

        mockery.checking(new Expectations() {{
            oneOf(session).save(objectToSave);
            will(returnValue(id));
        }});

        final GenericDao<Object> baseGenericDao = BaseGenericDao.create(Object.class, session);
        assertEquals(id, baseGenericDao.save(objectToSave));
    }

    @Test
    public void testRead() throws Exception {
        final Session session = mockery.mock(Session.class);
        final Object object = new Object();
        final Serializable exitingId = 123;
        final Serializable notExitingId = 123;

        mockery.checking(new Expectations() {{
            oneOf(session).get(Object.class, exitingId);
            will(returnValue(object));
            oneOf(session).get(Object.class, notExitingId);
            will(returnValue(null));
        }});

        final GenericDao<Object> baseGenericDao = BaseGenericDao.create(Object.class, session);
        assertEquals(object, baseGenericDao.read(exitingId));
        assertEquals(null, baseGenericDao.read(notExitingId));
    }

    @Test
    public void testDelete() throws Exception {
        final Session session = mockery.mock(Session.class);
        final Object object = new Object();

        mockery.checking(new Expectations() {{
            oneOf(session).delete(object);
        }});

        final GenericDao<Object> baseGenericDao = BaseGenericDao.create(Object.class, session);
        baseGenericDao.delete(object);
    }
}
