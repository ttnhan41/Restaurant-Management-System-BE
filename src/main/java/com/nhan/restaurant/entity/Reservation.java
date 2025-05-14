package com.nhan.restaurant.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private RestaurantTable table;

    private LocalDateTime reservationTime;
    private int guestsCount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING, CONFIRMED, CANCELLED
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public int getGuestsCount() {
        return guestsCount;
    }

    public Status getStatus() {
        return status;
    }
}
