package com.nhan.restaurant.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PLACED, IN_PROGRESS, SERVED, CANCELLED
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public Status getStatus() {
        return status;
    }
}
