package com.blogspot.fuud.java.bugtracker.domain;

import com.blogspot.fuud.java.bugtracker.dao.BaseTest;
import com.blogspot.fuud.java.bugtracker.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

public class CommentTest extends BaseTest{
    @Test
    public void store() throws Exception {
        final UserDao userDao = new UserDao(getSession());
        final User user = userDao.addUser("login", "password");
        Comment comment = new Comment(user, "text");

        getSession().beginTransaction();
        getSession().save(comment);
        getSession().getTransaction().commit();
        getSession().close();

        final Session session = getSessionFactory().openSession();
        session.beginTransaction();
        final Comment loadedComment = (Comment) session.get(Comment.class, comment.getId());
        session.getTransaction().commit();
        session.close();

        assertEquals(comment.getAuthor().getLogin(), loadedComment.getAuthor().getLogin());
        assertEquals(comment.getText(), loadedComment.getText());
    }
}
