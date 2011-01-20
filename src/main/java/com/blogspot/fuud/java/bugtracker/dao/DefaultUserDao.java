package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.User;
import com.blogspot.fuud.java.experemental.hibernate.dsl.DetachedCriteriaBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
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
        return getUser(login) != null;
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

        final DetachedCriteria criteria = new DetachedCriteriaBuilder<User>() {{
            where(eq(object.getLogin(), username));
        }}.getCriteria();

        final int maxResults = 1;
        final List users = getHibernateTemplate().findByCriteria(criteria, 0, maxResults);
        return users.size() == 0 ? null : (User) users.get(0);
    }

    @Override
    public List<User> getUsers() {
        return getHibernateTemplate().loadAll(User.class);
    }
}
