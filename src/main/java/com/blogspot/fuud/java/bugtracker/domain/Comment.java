package com.blogspot.fuud.java.bugtracker.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment{
    private Comment() {
    }

    public Comment(User author, String text) {
        if (author==null){
            throw new NullPointerException("author");
        }
        if (text==null){
            throw new NullPointerException("text");
        }
        this.author = author;
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne(optional = false)
    private User author;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Comment> replies = new HashSet<Comment>();

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        if (author==null){
            throw new NullPointerException("author");
        }
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
        if (text==null){
            throw new NullPointerException("text");
        }
        this.text = text;
    }
}
