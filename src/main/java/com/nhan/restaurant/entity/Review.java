package com.nhan.restaurant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private MenuItem menuItem;

    private int rating;
    private String comment;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
