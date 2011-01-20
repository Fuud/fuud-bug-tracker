package com.blogspot.fuud.java.bugtracker.dao;

import com.blogspot.fuud.java.bugtracker.domain.Project;
import com.blogspot.fuud.java.experemental.hibernate.dsl.DetachedCriteriaBuilder;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectDao extends BaseGenericDao<Project> {
    public boolean isProjectExists(String title) {
        return getProject(title) != null;
    }

    public Project getProject(final String title) {
        final DetachedCriteria criteria = new DetachedCriteriaBuilder<Project>() {{
            where(eq(object.getTitle(), title));
        }}.getCriteria();

        final int maxResults = 1;
        final List projects = getHibernateTemplate().findByCriteria(criteria, 0, maxResults);
        return projects.size() == 0 ? null : (Project) projects.get(0);
    }

    public Project addProject(String title) {
        final Project project = new Project();
        project.setTitle(title);
        getHibernateTemplate().save(project);
        return project;
    }
}
