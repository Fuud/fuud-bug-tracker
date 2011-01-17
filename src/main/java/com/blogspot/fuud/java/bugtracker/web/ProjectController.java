package com.blogspot.fuud.java.bugtracker.web;

import com.blogspot.fuud.java.bugtracker.dao.ProjectDao;
import com.blogspot.fuud.java.bugtracker.domain.Project;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(ProjectController.class);

    private ProjectDao projectDao;

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @RequestMapping("addProject.do")
    public ModelMap addUser(@RequestParam(required = false) String title) {

        String message;

        if (title == null) {
            message = ""; // init
        } else if (title.trim().isEmpty()) {
            message = "Project title should not be empty.";
        } else {
            title = title.trim();
            if (projectDao.isProjectExists(title)) {
                message = "Project " + title + " is already exists";
            } else {
                final Project project = projectDao.addProject(title);
                message = "Project " + project.getTitle() + " created successfully";
            }
        }

        return new ModelMap("message", message);
    }
}
