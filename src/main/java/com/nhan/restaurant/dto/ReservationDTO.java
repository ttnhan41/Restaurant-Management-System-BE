package com.nhan.restaurant.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private Long userId;
    private Long tableId;
    private LocalDateTime reservationTime;
    private int guestsCount;
    private String status;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public void setGuestsCount(int guestsCount) {
        this.guestsCount = guestsCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
