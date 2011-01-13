package com.blogspot.fuud.java.bugtracker.dao;

import java.io.Serializable;

public interface GenericDao<T> {
    Serializable save(T newInstance);

    T read(Serializable id);

    void delete(T persistentObject);
}