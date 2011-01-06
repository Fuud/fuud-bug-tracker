package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.Issue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class IssuesDao extends BaseGenericDao<Issue>{
    public IssuesDao(SessionFactory sessionFactory) {
        super(Issue.class, sessionFactory);
    }
}
