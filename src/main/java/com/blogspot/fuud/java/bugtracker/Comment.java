package com.blogspot.fuud.java.bugtracker;

import java.util.Set;

public class Comment extends User {
    private String text;
    private User author;
    private Set<Comment> replies;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Comment> getReplies() {
        return replies;
    }

    public void setReplies(Set<Comment> replies) {
        this.replies = replies;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
