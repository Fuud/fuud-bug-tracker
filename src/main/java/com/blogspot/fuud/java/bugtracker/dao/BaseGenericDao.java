package com.blogspot.fuud.java.bugtracker.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseGenericDao<T> extends HibernateDaoSupport{
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
}
