package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.Issue;
import org.hibernate.Session;

public class IssuesDao extends BaseGenericDao<Issue>{
    public IssuesDao(Session session) {
        super(Issue.class, session);
    }
}
