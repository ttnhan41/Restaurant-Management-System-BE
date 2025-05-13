package com.nhan.restaurant.dto;

public class RestaurantTableDTO {
    private Long id;
    private int tableNumber;
    private int seats;
    private boolean available;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
