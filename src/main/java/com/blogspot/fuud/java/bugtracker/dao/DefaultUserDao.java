package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
@Transactional
public class DefaultUserDao implements UserDao {
    private final BaseGenericDao<User> baseGenericDao;

    public DefaultUserDao(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new NullPointerException("session");
        }
        baseGenericDao = new BaseGenericDao<User>(User.class, sessionFactory);
    }

    @Override
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

    @Override
    public User addUser(String loginName, String password) {
        final User user = new User();
        user.setLogin(loginName);
        user.setPassword(password);
        baseGenericDao.save(user);
        return user;
    }

    @Override
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
