package com.blogspot.fuud.java.bugtracker.web;

import com.blogspot.fuud.java.bugtracker.dao.UserDao;
import com.blogspot.fuud.java.bugtracker.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ListUsersController {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("listUsers.do")
    public ModelMap listUsers() {
        List<User> users = userDao.getUsers();
        return new ModelMap("usersList", users);
    }
}
