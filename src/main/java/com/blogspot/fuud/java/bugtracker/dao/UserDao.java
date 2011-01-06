package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class UserDao {
    private final BaseGenericDao<User> baseGenericDao;

    public UserDao(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new NullPointerException("session");
        }
        baseGenericDao = new BaseGenericDao<User>(User.class, sessionFactory);
    }

    public boolean isUserExists(final String login) {
        return baseGenericDao.getHibernateTemplate().execute(
                new HibernateCallback<Boolean>() {
                    public Boolean doInHibernate(Session session) {
                        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
                        return user != null;
                    }
                }
        );
    }

    public User addUser(String loginName, String password) {
        final User user = new User();
        user.setLogin(loginName);
        user.setPassword(password);
        baseGenericDao.save(user);
        return user;
    }

    public boolean isCredentialsValid(final String login, final String password) {
        return baseGenericDao.getHibernateTemplate().execute(
                new HibernateCallback<Boolean>() {
                    public Boolean doInHibernate(Session session) {
                        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
                        return user != null && password.equals(user.getPassword());
                    }
                }
        );
    }
}
