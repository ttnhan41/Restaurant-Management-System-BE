package com.nhan.restaurant.dto;

import com.nhan.restaurant.entity.Reservation;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private Long userId;
    private String customerName;
    private Long tableId;
    private int tableNumber;
    private LocalDateTime reservationTime;
    private int guestsCount;
    private Reservation.Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getGuestsCount() {
        return guestsCount;
    }

    public void setGuestsCount(int guestsCount) {
        this.guestsCount = guestsCount;
    }

    public Reservation.Status getStatus() {
        return status;
    }

    public void setStatus(Reservation.Status status) {
        this.status = status;
    }
}
