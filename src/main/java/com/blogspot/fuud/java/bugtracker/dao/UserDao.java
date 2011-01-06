package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.User;

public interface UserDao {
    boolean isUserExists(String login);

    User addUser(String loginName, String password);

    boolean isCredentialsValid(String login, String password);
}
