package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserDao extends HibernateDaoSupport implements UserDao {
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }

    @Override
    public boolean isUserExists(final String login) {
        return getHibernateTemplate().execute(
                new HibernateCallback<Boolean>() {
                    public Boolean doInHibernate(Session session) {
                        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
                        return user != null;
                    }
                }
        );
    }

    @Override
    public User addUser(String loginName, String password) {
        final User user = new User();
        user.setLogin(loginName);
        user.setPassword(password);
        getHibernateTemplate().save(user);
        return user;
    }

    @Override
    public boolean isCredentialsValid(final String username, final String password) {
        User user = getUser(username);
        return user != null && password.equals(user.getPassword());
    }

    @Override
    public User getUser(final String username) {
        return getHibernateTemplate().execute(
                new HibernateCallback<User>() {
                    public User doInHibernate(Session session) {
                        return (User) session.createCriteria(User.class).add(Restrictions.eq("login", username)).uniqueResult();
                    }
                }
        );
    }

    @Override
    public List<User> getUsers() {
        return getHibernateTemplate().loadAll(User.class);
    }
}
