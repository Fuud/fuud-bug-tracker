package com.blogspot.fuud.java.bugtracker.web;

import com.blogspot.fuud.java.bugtracker.dao.UserDao;
import com.blogspot.fuud.java.bugtracker.domain.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SumController extends AbstractController {
    private static volatile int counter = 0;
    private UserDao userDao;

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("calc.jsp");
        final User user = userDao.addUser("login" + counter, "");
        counter++;
        mav.addObject("id", user.getId());
        return mav;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}