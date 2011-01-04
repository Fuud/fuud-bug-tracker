package com.blogspot.fuud.java.bugtracker.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

public class CommentTest {
    private SessionFactory sessionFactory;

	@Before
	public void setUp() throws Exception {
		// A SessionFactory is set up once for an application
        final Configuration configuration = new Configuration();
        sessionFactory = configuration
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
	}

	@After
	public  void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
    @Test
    public void store() throws Exception {
        Comment comment = new Comment();
        //comment.setAuthor(new User());
        comment.setReplies(new HashSet<Comment>());
        final String text = "text of the comment";
        comment.setText(text);

        final Long id = save(comment);
        Comment loadedComment = load(id);

        System.out.println(loadedComment.getText());
    }

    @Test
    public void storeReplies() throws Exception {
        Comment comment = commentWithText("text of the comment");

        Comment reply = commentWithText("text of the reply");
        Comment reply2 = commentWithText("text of the reply2");

        comment.getReplies().add(reply);
        comment.getReplies().add(reply2);

        save(reply);
        save(reply2);
        final Long id = save(comment);
        Comment loadedComment = load(id);

        for (Comment loadedReply : loadedComment.getReplies()) {
            System.out.println(loadedReply.getText());
        }
    }

    private Comment commentWithText(String text) {
        Comment comment = new Comment();
        //comment.setAuthor(new User());
        comment.setReplies(new HashSet<Comment>());
        comment.setText(text);
        return comment;
    }

    private Comment load(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Comment loadedComment = (Comment) session.createQuery("from Comment c where c.id=:id").setLong("id", id).uniqueResult();

        session.getTransaction().commit();
        session.close();
        return loadedComment;
    }

    private Long save(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(comment);
        final Long id = comment.getId();
        System.out.println(id);

        session.getTransaction().commit();
        session.close();
        return id;
    }
}
