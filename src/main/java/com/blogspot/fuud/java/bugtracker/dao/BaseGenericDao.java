package com.blogspot.fuud.java.bugtracker.dao;

import org.hibernate.Session;

import java.io.Serializable;

public class BaseGenericDao<T> implements GenericDao<T> {
    private final Session session;
    private final Class<T> dataType;

    public static <TStatic> BaseGenericDao<TStatic> create(Class<TStatic> dataType, Session session){
        return new BaseGenericDao<TStatic>(dataType, session);
    }

    public BaseGenericDao(Class<T> dataType, Session session) {
        this.session = session;
        this.dataType = dataType;
    }

    @Override
    public Serializable save(T newInstance) {
        return session.save(newInstance);
    }

    @Override
    public T read(Serializable id) {
        return dataType.cast(session.get(dataType, id));
    }

    @Override
    public void delete(T persistentObject) {
        session.delete(persistentObject);
    }
}
