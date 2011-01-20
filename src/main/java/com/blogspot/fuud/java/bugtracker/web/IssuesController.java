package com.blogspot.fuud.java.bugtracker.web;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class IssuesController {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(IssuesController.class);

    @RequestMapping
    public ModelMap addIssue(@RequestParam(required = false) String title) {
        return null;
    }
}
