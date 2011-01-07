package com.blogspot.fuud.java.bugtracker.web;

import com.blogspot.fuud.java.bugtracker.dao.UserDao;
import com.blogspot.fuud.java.bugtracker.domain.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListUsersController extends AbstractController {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = userDao.getUsers();
        ModelAndView mv = new ModelAndView("/WEB-INF/pages/listUsers.jsp");
        mv.addObject("usersList", users);
        return mv;
    }
}
