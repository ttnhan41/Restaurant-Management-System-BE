package com.nhan.restaurant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tableNumber;
    private int seats;
    private boolean available;

    public Long getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getSeats() {
        return seats;
    }

    public boolean isAvailable() {
        return available;
    }
}
