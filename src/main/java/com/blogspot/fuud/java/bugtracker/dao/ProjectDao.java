package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.Project;
import com.blogspot.fuud.java.bugtracker.domain.User;
import org.hibernate.Session;

public class ProjectDao extends BaseGenericDao<Project>{
    public ProjectDao(Session session) {
        super(Project.class, session);
    }
}
