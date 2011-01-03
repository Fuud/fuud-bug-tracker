package com.blogspot.fuud.java.bugtracker;

public class ResolvedIssue extends Issue {
    private Resolution resolution;
    private boolean isClosed;

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }
}
