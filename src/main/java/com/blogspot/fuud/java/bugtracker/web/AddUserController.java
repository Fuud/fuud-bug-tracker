package com.blogspot.fuud.java.bugtracker.web;

import com.blogspot.fuud.java.bugtracker.dao.UserDao;
import com.blogspot.fuud.java.bugtracker.domain.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserController extends AbstractController {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

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

        ModelAndView mv = new ModelAndView("addUser.jsp");
        mv.addObject("message", message);
        return mv;
    }
}