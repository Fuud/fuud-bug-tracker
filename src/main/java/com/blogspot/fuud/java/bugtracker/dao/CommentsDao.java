package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.Comment;
import org.hibernate.Session;

public class CommentsDao extends BaseGenericDao<Comment>{
    public CommentsDao(Session session) {
        super(Comment.class, session);
    }
}
