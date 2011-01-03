package com.blogspot.fuud.java.bugtracker;

import java.util.Collection;
import java.util.Set;

public class Issue {
    private String title;
    private IssueType type;
    private IssueState issueState;
    private boolean isSuspended;

    private User assigner;
    private User reporter;

    private Set<Issue> blockers;
    private Set<Issue> clones;

    private Set<Comment> comments;

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public Set<Issue> getBlockers() {
        return blockers;
    }

    public void setBlockers(Set<Issue> blockers) {
        this.blockers = blockers;
    }

    public Set<Issue> getClones() {
        return clones;
    }

    public void setClones(Set<Issue> clones) {
        this.clones = clones;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public IssueState getIssueState() {
        return issueState;
    }

    public void setIssueState(IssueState issueState) {
        this.issueState = issueState;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }
}
