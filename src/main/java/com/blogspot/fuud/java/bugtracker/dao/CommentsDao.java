package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CommentsDao extends BaseGenericDao<Comment>{
    public CommentsDao(SessionFactory sessionFactory) {
        super(Comment.class, sessionFactory);
    }
}
