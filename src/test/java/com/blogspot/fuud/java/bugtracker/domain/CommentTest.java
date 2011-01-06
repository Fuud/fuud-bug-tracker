package com.blogspot.fuud.java.bugtracker.domain;

import com.blogspot.fuud.java.bugtracker.dao.BaseTest;
import com.blogspot.fuud.java.bugtracker.dao.DefaultUserDao;
import com.blogspot.fuud.java.bugtracker.dao.UserDao;
import org.hibernate.Session;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

public class CommentTest extends BaseTest{
    @Test
    public void store() throws Exception {
        final UserDao userDao = new DefaultUserDao(getSessionFactory());
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
