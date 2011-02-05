package com.blogspot.fuud.java.bugtracker.web;

import com.blogspot.fuud.java.bugtracker.dao.UserDao;
import com.blogspot.fuud.java.bugtracker.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("addUser")
    public ModelMap addUser(@RequestParam(required = false) String username,
                            @RequestParam(required = false) String password) {

        String message;

        if (username == null) {
            message = ""; // init
        } else if (username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            message = "Login and password should not be empty.";
        } else {
            username = username.trim();
            password = password.trim();
            if (userDao.isUserExists(username)) {
                message = "User with username " + username + " is already exists";
            } else {
                final User user = userDao.addUser(username, password);
                message = "User " + user.getLogin() + " with password " + user.getPassword() + " created successfully";
            }
        }

        return new ModelMap("message", message);
    }

    @RequestMapping("listUsers")
    public ModelMap listUsers() {
        List<User> users = userDao.getUsers();
        return new ModelMap("usersList", users);
    }
}