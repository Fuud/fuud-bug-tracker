package com.blogspot.fuud.java.bugtracker;

import org.apache.log4j.Logger;

import java.util.Collection;

public class Issue {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(Issue.class);

    private IssueType type;
    private IssueState state;
    private Collection<Issue> blockers;
    private Collection<Issue> clones;

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public IssueState getState() {
        return state;
    }

    public void setState(IssueState state) {
        this.state = state;
    }

    public Collection<Issue> getBlockers() {
        return blockers;
    }

    public void setBlockers(Collection<Issue> blockers) {
        this.blockers = blockers;
    }

    public Collection<Issue> getClones() {
        return clones;
    }

    public void setClones(Collection<Issue> clones) {
        this.clones = clones;
    }
}
