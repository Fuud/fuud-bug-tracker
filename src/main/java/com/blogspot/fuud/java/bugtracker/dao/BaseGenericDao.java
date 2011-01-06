package com.blogspot.fuud.java.bugtracker.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;

public class BaseGenericDao<T> implements GenericDao<T> {
    private final Class<T> dataType;
    private final HibernateTemplate hibernateTemplate;

    public static <TStatic> BaseGenericDao<TStatic> create(Class<TStatic> dataType, SessionFactory sessionFactory){
        return new BaseGenericDao<TStatic>(dataType, sessionFactory);
    }

    protected HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public BaseGenericDao(Class<T> dataType, SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
        this.dataType = dataType;
    }

    @Override
    public Serializable save(T newInstance) {
        return hibernateTemplate.save(newInstance);
    }

    @Override
    public T read(Serializable id) {
        return hibernateTemplate.get(dataType, id);
    }

    @Override
    public void delete(T persistentObject) {
        hibernateTemplate.delete(persistentObject);
    }
}
