package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDao {
    private final GenericDao<User> baseGenericDao;
    private final Session session;

    public UserDao(Session session) {
        if (session==null){
            throw new NullPointerException("session");
        }
        this.session = session;
        baseGenericDao = new BaseGenericDao<User>(User.class, session);
    }

    public boolean isUserExists(String loginName) {
        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", loginName)).uniqueResult();
        return user != null;
    }

    public User addUser(String loginName, String password){
        final User user = new User();
        user.setLogin(loginName);
        user.setPassword(password);
        baseGenericDao.save(user);
        return user;
    }

    public boolean isCredentialsValid(String login, String password){
        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
        return user!=null && password.equals(user.getPassword());
    }
}
