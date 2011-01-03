package com.blogspot.fuud.java.bugtracker;

import java.util.Set;

public class Project {
    private String title;
    private Set<Issue> issues;

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
