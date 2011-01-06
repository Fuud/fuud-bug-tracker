package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.User;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import static junit.framework.Assert.*;

public class DefaultUserDaoTest extends BaseTest {
    @Test
    public void testIsUserExists() throws Exception {
        final String userName = "abracadabra";
        final String userPassword = "password666";

        DefaultUserDao userDao = new DefaultUserDao();
        userDao.setSessionFactory(getSessionFactory());

        assertFalse(userDao.isUserExists(userName));

        User user = userDao.addUser(userName, userPassword);

        assertEquals(userName, user.getLogin());
        assertEquals(userPassword, user.getPassword());

        assertTrue(userDao.isUserExists(userName));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldBeImpossibleToAddTwoUsersWithSameLogin() throws Exception {
        final String userName = "abracadabra";
        final String userPassword = "password666";
        final String userPassword2 = "password777";

        DefaultUserDao userDao = new DefaultUserDao();
        userDao.setSessionFactory(getSessionFactory());

        userDao.addUser(userName, userPassword);
        userDao.addUser(userName, userPassword2);
    }

    @Test
    public void isCredentialsValid() throws Exception {
        final String userName = "abracadabra";
        final String userName2 = "abracadabra2";
        final String userPassword = "password666";
        final String userPassword2 = "password777";

        DefaultUserDao userDao = new DefaultUserDao();
        userDao.setSessionFactory(getSessionFactory());

        userDao.addUser(userName, userPassword);

        assertTrue(userDao.isCredentialsValid(userName, userPassword));
        assertFalse(userDao.isCredentialsValid(userName, userPassword2));
        assertFalse(userDao.isCredentialsValid(userName2, userPassword));
        assertFalse(userDao.isCredentialsValid(userName2, userPassword2));

        userDao.addUser(userName2, userPassword2);

        assertTrue(userDao.isCredentialsValid(userName, userPassword));
        assertFalse(userDao.isCredentialsValid(userName, userPassword2));
        assertFalse(userDao.isCredentialsValid(userName2, userPassword));
        assertTrue(userDao.isCredentialsValid(userName2, userPassword2));
    }
}
