package com.blogspot.fuud.java.bugtracker.dao;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;

public class UserDaoTest extends BaseTest{
    private UserDao userDao;

    @Test
    public void testIsUserExists() throws Exception {
        final String userName = "abracadabra";
        userDao = new UserDao(getSession());

        assertFalse(userDao.isUserExists(userName));
    }
}
