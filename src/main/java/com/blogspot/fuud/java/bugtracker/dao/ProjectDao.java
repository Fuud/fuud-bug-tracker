package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.Project;
import com.blogspot.fuud.java.bugtracker.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProjectDao extends BaseGenericDao<Project>{
    public ProjectDao(SessionFactory sessionFactory) {
        super(Project.class, sessionFactory);
    }
}
